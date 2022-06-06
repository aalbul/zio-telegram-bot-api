package com.github.aalbul.zio.telegram

import com.github.aalbul.zio.telegram.Bot.BotConfig
import com.github.aalbul.zio.telegram.LongPollingBot.{ApiCommandExecutionException, BotException, ToJsonOps}
import com.github.aalbul.zio.telegram.domain.command.{ApiResponse, CopyMessageRequest, CopyMessageResponse, ForwardMessageRequest, GetMeResponse, GetUpdatesRequest, GetUpdatesResponse, MessageProductionResponse, SendMessageRequest}
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
  private def callApi[Response <: ApiResponse: Decoder](
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
    response <- ZIO.fromEither(parse(bodyJson).flatMap(_.as[Response]))
    _ <- ZIO
      .fail(ApiCommandExecutionException(command, s"API returned NOK. Reason: ${response.description}"))
      .unless(response.ok)
  } yield response

  override def getMe: Task[User] = for {
    response <- callApi[GetMeResponse](command = "getMe")
    user <- ZIO.fromOption(response.result).orElseFail(BotException("Error getting information about the bot"))
  } yield user

  override def getUpdates(request: GetUpdatesRequest): Task[Seq[Update]] = callApi[GetUpdatesResponse](
    command = "getUpdates",
    bodyJson = Some(request.toJson)
  ).map(_.result)

  override def stream: ZStream[Any, Throwable, Update] = ZStream.unfoldChunkZIO(0) { offset =>
    for {
      updates <- getUpdates(GetUpdatesRequest.empty.withLimit(botConfig.chunkSize).withOffset(offset))
      newOffset = updates.lastOption.map(_.updateId + 1).getOrElse(offset)
    } yield Some(Chunk.fromIterable(updates), newOffset)
  }

  override def sendMessage(request: SendMessageRequest): Task[Message] = for {
    response <- callApi[MessageProductionResponse](
      command = "sendMessage",
      bodyJson = Some(request.toJson)
    )
    message <- ZIO.fromOption(response.result).orElseFail(BotException("Error sending message"))
  } yield message

  override def forwardMessage(request: ForwardMessageRequest): Task[Message] = for {
    response <- callApi[MessageProductionResponse](
      command = "forwardMessage",
      bodyJson = Some(request.toJson)
    )
    message <- ZIO.fromOption(response.result).orElseFail(BotException("Error forwarding message"))
  } yield message

  override def copyMessage(request: CopyMessageRequest): Task[MessageId] = for {
    response <- callApi[CopyMessageResponse](
      command = "copyMessage",
      bodyJson = Some(request.toJson)
    )
    messageId <- ZIO.fromOption(response.result).orElseFail(BotException("Error forwarding message"))
  } yield messageId
}
