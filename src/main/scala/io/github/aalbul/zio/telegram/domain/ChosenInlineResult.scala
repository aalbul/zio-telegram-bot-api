package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

@ConfiguredJsonCodec(decodeOnly = true)
case class ChosenInlineResult(
  resultId: String,
  from: User,
  location: Option[Location],
  inlineMessageId: Option[String],
  query: String
)
