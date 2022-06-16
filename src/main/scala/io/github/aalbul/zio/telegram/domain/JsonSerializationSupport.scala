package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.Configuration
import io.circe.{Decoder, Encoder}

import java.time.Instant

trait JsonSerializationSupport {
  implicit val config: Configuration = Configuration.default.withSnakeCaseMemberNames
  implicit val decodeInstant: Decoder[Instant] = Decoder.decodeLong.map(Instant.ofEpochSecond)
  implicit val encodeInstant: Encoder[Instant] = Encoder.encodeLong.contramap(_.getEpochSecond)

  implicit class StringOps(string: String) {
    def camelToSnakeCase: String = string.split("(?<=.)(?=\\p{Lu})").map(_.toLowerCase).mkString("_")
    def capitalize: String = s"${string.headOption.map(_.toUpper).getOrElse("")}${string.tail}"
  }
}
