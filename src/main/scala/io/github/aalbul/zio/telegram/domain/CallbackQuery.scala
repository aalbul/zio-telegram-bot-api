package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

@ConfiguredJsonCodec(decodeOnly = true)
case class CallbackQuery(
  id: String,
  from: User,
  message: Option[Message],
  inlineMessageId: Option[String],
  chatInstance: String,
  data: Option[String],
  gameShortName: Option[String]
)
