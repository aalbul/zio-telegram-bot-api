package io.github.aalbul.zio.telegram.engine

import io.github.aalbul.zio.telegram.command.*
import io.github.aalbul.zio.telegram.engine.BotEngine.ApiCommandExecutionException
import io.circe.Decoder
import io.circe.parser.parse
import sttp.capabilities.zio.ZioStreams
import sttp.client3.{basicRequest, multipart, multipartFile, Identity, RequestT, SttpBackend, UriContext}
import sttp.model.MediaType
import zio.{Task, URLayer, ZIO, ZLayer}

object SttpBasedBotEngine {
  val layer: URLayer[SttpBackend[Task, ZioStreams] & BotConfig, BotEngine] =
    ZLayer.fromFunction(new SttpBasedBotEngine(_, _))
}

class SttpBasedBotEngine(backend: SttpBackend[Task, ZioStreams], botConfig: BotConfig) extends BotEngine {
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
}
