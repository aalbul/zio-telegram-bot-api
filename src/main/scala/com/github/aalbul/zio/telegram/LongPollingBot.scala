package com.github.aalbul.zio.telegram

import com.github.aalbul.zio.telegram.Bot.BotConfig
import com.github.aalbul.zio.telegram.LongPollingBot.{ApiCommandExecutionException, ToJsonOps}
import com.github.aalbul.zio.telegram.domain.command.{ApiResponse, CopyMessageRequest, FailureApiResponse, ForwardMessageRequest, GetUpdatesRequest, SendMessageRequest, SuccessApiResponse}
import com.github.aalbul.zio.telegram.domain.{Message, MessageId, Update, User}
import io.circe.parser.*
import io.circe.syntax.EncoderOps
import io.circe.{Decoder, Encoder, Printer}
import sttp.capabilities.zio.ZioStreams
import sttp.client3.*
import sttp.model.MediaType
import zio.stream.ZStream
import zio.{Chunk, Task, ZIO}

object LongPollingBot {
  case class BotException(message: String) extends Exception(message)

  case class ApiCommandExecutionException(command: String, error: String)
    extends Exception(s"Failed executing $command api command: $error")

  private val jsonPrinter: Printer = Printer.noSpaces.copy(dropNullValues = true)

  implicit class ToJsonOps[T: Encoder](entity: T) {
    def toJson: String = entity.asJson.printWith(jsonPrinter)
  }
}

class LongPollingBot(backend: SttpBackend[Task, ZioStreams], botConfig: BotConfig) extends Bot {
  private def callApi[Response: Decoder](
    command: String,
    bodyJson: Option[String] = None
  ): Task[Response] = for {
    request <- ZIO.succeed(
      basicRequest
        .post(uri"https://api.telegram.org/bot${botConfig.token}/$command")
        .contentType(MediaType.ApplicationJson)
    )
    response <- backend.send(bodyJson.map(body => request.body(body)).getOrElse(request))
    bodyJson <- ZIO.succeed(response.body.fold(identity, identity))
    response <- ZIO.fromEither(parse(bodyJson).flatMap(_.as[ApiResponse[? <: Response]]))
    result <- response match {
      case failure: FailureApiResponse =>
        ZIO
          .fail(
            ApiCommandExecutionException(
              command = command,
              error = s"API returned NOK. Code: ${failure.errorCode}, reason: ${failure.description}"
            )
          )
      case SuccessApiResponse(result) => ZIO.succeed(result)
    }
  } yield result

  override def getMe: Task[User] = callApi[User](command = "getMe")

  override def getUpdates(request: GetUpdatesRequest): Task[Seq[Update]] = callApi[Seq[Update]](
    command = "getUpdates",
    bodyJson = Some(request.toJson)
  )

  override def stream: ZStream[Any, Throwable, Update] = ZStream.unfoldChunkZIO(0) { offset =>
    for {
      updates <- getUpdates(GetUpdatesRequest.empty.withLimit(botConfig.chunkSize).withOffset(offset))
      newOffset = updates.lastOption.map(_.updateId + 1).getOrElse(offset)
    } yield Some(Chunk.fromIterable(updates), newOffset)
  }

  override def sendMessage(request: SendMessageRequest): Task[Message] = callApi[Message](
    command = "sendMessage",
    bodyJson = Some(request.toJson)
  )

  override def forwardMessage(request: ForwardMessageRequest): Task[Message] = callApi[Message](
    command = "forwardMessage",
    bodyJson = Some(request.toJson)
  )

  override def copyMessage(request: CopyMessageRequest): Task[MessageId] = callApi[MessageId](
    command = "copyMessage",
    bodyJson = Some(request.toJson)
  )
}
