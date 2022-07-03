package io.github.aalbul.zio.telegram.engine

import io.circe.Decoder
import io.circe.parser.parse
import io.github.aalbul.zio.telegram.command.*
import io.github.aalbul.zio.telegram.engine.BotEngine.{ApiCommandExecutionException, BotException}
import io.github.aalbul.zio.telegram.engine.SttpBasedBotEngine.*
import sttp.capabilities.WebSockets
import sttp.capabilities.zio.ZioStreams
import sttp.client3.{Identity, RequestT, SttpBackend, UriContext, asStreamUnsafe, basicRequest, multipart, multipartFile}
import sttp.model.MediaType
import zio.stream.ZStream
import zio.{Task, URLayer, ZIO, ZLayer}

import scala.concurrent.duration.Duration

object SttpBasedBotEngine {
  type SttpClient = SttpBackend[Task, ZioStreams & WebSockets]

  val layer: URLayer[SttpClient & BotConfig, BotEngine] = ZLayer.fromFunction(new SttpBasedBotEngine(_, _))
}

class SttpBasedBotEngine(backend: SttpClient, botConfig: BotConfig) extends BotEngine {
  private def prepareRequest[T](command: Command[T]): Task[RequestT[Identity, Either[String, String], Any]] =
    ZIO.succeed {
      val base = basicRequest.post(uri"https://api.telegram.org/bot${botConfig.token}/${command.name}")
      command.parameters match {
        case NoParameters      => base
        case body: JsonBody[?] => base.contentType(MediaType.ApplicationJson).body(body.toJson)
        case body: MultipartBody =>
          base
            .contentType(MediaType.MultipartFormData)
            .multipartBody(body.parts.map {
              case string: StringPart => multipart(string.name, string.value)
              case file: FilePart     => multipartFile(file.name, file.path)
            })
      }
    }

  override def execute[T: Decoder](command: Command[T]): Task[T] = for {
    request <- prepareRequest(command)
    bodyJson <- backend.send(request).map(_.body.fold(identity, identity))
    response <- ZIO.fromEither(parse(bodyJson).flatMap(_.as[ApiResponse[? <: T]]))
    result <- response match {
      case failure: FailureApiResponse =>
        ZIO
          .fail(
            ApiCommandExecutionException(
              command = command.name,
              error = s"API returned NOK. Code: ${failure.errorCode}, reason: ${failure.description}"
            )
          )
      case SuccessApiResponse(result) => ZIO.succeed(result)
    }
  } yield result

  override def streamFile(path: String): Task[ZStream[Any, Throwable, Byte]] = for {
    request <- ZIO.succeed(
      basicRequest
        .get(uri"https://api.telegram.org/file/bot${botConfig.token}/$path")
        .response(asStreamUnsafe(ZioStreams))
        .readTimeout(Duration.Inf)
    )
    response <- backend.send(request)
    body <- ZIO.fromEither(response.body.left.map(BotException))
  } yield body
}
