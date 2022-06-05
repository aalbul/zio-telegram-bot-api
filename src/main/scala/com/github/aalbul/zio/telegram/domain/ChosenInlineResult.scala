package com.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
case class ChosenInlineResult(
  resultId: String,
  from: User,
  location: Option[Location],
  inlineMessageId: Option[String],
  query: String
)
