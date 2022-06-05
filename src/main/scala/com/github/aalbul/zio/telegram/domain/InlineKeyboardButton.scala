package com.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec
case class InlineKeyboardButton(
  text: String,
  url: Option[String],
  callbackData: Option[String],
  webApp: Option[WebAppInfo],
  switchInlineQuery: Option[String],
  switchInlineQueryCurrentChat: Option[String],
  callbackGame: Option[CallbackGame],
  pay: Option[Boolean]
)
