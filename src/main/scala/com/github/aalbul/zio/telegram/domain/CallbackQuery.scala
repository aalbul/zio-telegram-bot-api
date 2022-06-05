package com.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

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
