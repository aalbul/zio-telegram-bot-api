package com.github.aalbul.zio.telegram

import com.github.aalbul.zio.telegram.Bot.BotConfig
import com.github.aalbul.zio.telegram.LongPollingBot.*
import com.github.aalbul.zio.telegram.domain.command.{ApiResponse, CopyMessageRequest, FailureApiResponse, FileDescriptor, ForwardMessageRequest, GetUpdatesRequest, IdFileDescriptor, PathBasedFileDescriptor, SendMessageRequest, SendPhotoRequest, SuccessApiResponse, UrlFileDescriptor}
import com.github.aalbul.zio.telegram.domain.{Message, MessageId, Update, User}
import io.circe.parser.*
import io.circe.syntax.EncoderOps
import io.circe.{Decoder, Encoder, Printer}
import sttp.capabilities.zio.ZioStreams
import sttp.client3.{basicRequest, multipart, multipartFile, RequestBody, SttpBackend, UriContext}
import sttp.model.{MediaType, Part}
import zio.stream.ZStream
import zio.{Chunk, Task, ZIO}

object LongPollingBot {
  case class BotException(message: String) extends Exception(message)

  case class ApiCommandExecutionException(command: String, error: String)
    extends Exception(s"Failed executing $command api command: $error")

  private val jsonPrinter: Printer = Printer.noSpaces.copy(dropNullValues = true)

  sealed trait Body
  case object EmptyBody extends Body
  case class JsonBody[T: Encoder](body: T) extends Body {
    def toJson: String = body.asJson.printWith(jsonPrinter)
  }
  object MultipartBody {
    def of(parts: Option[Part[RequestBody[Any]]]*) = new MultipartBody(parts.flatten)
  }
  case class MultipartBody(parts: Seq[Part[RequestBody[Any]]]) extends Body

  implicit class FileDescriptorOps(descriptor: FileDescriptor) {
    def asMultipart(name: String): Option[Part[RequestBody[Any]]] = Some(descriptor match {
      case PathBasedFileDescriptor(path) => multipartFile(name, path)
      case UrlFileDescriptor(url)        => multipart(name, url)
      case IdFileDescriptor(id)          => multipart(name, id)
    })
  }
}

class LongPollingBot(backend: SttpBackend[Task, ZioStreams], botConfig: BotConfig) extends Bot {
  private def callApi[Response: Decoder](
    command: String,
    body: Body = EmptyBody
  ): Task[Response] = for {
    request <- ZIO.succeed {
      val base = basicRequest
        .post(uri"https://api.telegram.org/bot${botConfig.token}/$command")
      body match {
        case EmptyBody                => base
        case body: JsonBody[?]        => base.contentType(MediaType.ApplicationJson).body(body.toJson)
        case multipart: MultipartBody => base.contentType(MediaType.MultipartFormData).multipartBody(multipart.parts)
      }
    }
    bodyJson <- backend.send(request).map(_.body.fold(identity, identity))
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
    body = JsonBody(request)
  )

  override def stream: ZStream[Any, Throwable, Update] = ZStream.unfoldChunkZIO(0) { offset =>
    for {
      updates <- getUpdates(GetUpdatesRequest.empty.withLimit(botConfig.chunkSize).withOffset(offset))
      newOffset = updates.lastOption.map(_.updateId + 1).getOrElse(offset)
    } yield Some(Chunk.fromIterable(updates), newOffset)
  }

  override def sendMessage(request: SendMessageRequest): Task[Message] = callApi[Message](
    command = "sendMessage",
    body = JsonBody(request)
  )

  override def forwardMessage(request: ForwardMessageRequest): Task[Message] = callApi[Message](
    command = "forwardMessage",
    body = JsonBody(request)
  )

  override def copyMessage(request: CopyMessageRequest): Task[MessageId] = callApi[MessageId](
    command = "copyMessage",
    body = JsonBody(request)
  )

  override def sendPhoto(request: SendPhotoRequest): Task[Message] = callApi[Message](
    command = "sendPhoto",
    body = MultipartBody.of(
      Some(multipart("chat_id", request.chatId)),
      request.photo.asMultipart("photo"),
      request.caption.map(multipart("caption", _)),
      request.parseMode.map(mode => multipart("parse_mode", mode.toString)),
      request.captionEntities.map(entities => multipart("caption_entities", JsonBody(entities).toJson)),
      request.disableNotification.map(disable => multipart("disable_notification", disable.toString)),
      request.protectContent.map(protect => multipart("protect_content", protect.toString)),
      request.replyToMessageId.map(id => multipart("reply_to_message_id", id.toString)),
      request.allowSendingWithoutReply.map(allow => multipart("allow_sending_without_reply", allow.toString)),
      request.replyMarkup.map(markup => multipart("reply_markup", JsonBody(markup).toJson))
    )
  )
}
