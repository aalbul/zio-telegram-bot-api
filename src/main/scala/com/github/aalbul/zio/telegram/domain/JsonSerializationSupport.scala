package com.github.aalbul.zio.telegram.domain

import io.circe.Decoder
import io.circe.generic.extras.Configuration

import java.time.Instant

trait JsonSerializationSupport {
  implicit val config: Configuration = Configuration.default.withSnakeCaseMemberNames
  implicit val decodeInstant: Decoder[Instant] = Decoder.decodeLong.map(Instant.ofEpochSecond)
}
