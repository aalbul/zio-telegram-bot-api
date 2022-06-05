package com.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(encodeOnly = true)
case class ReplyKeyboardMarkup(
  keyboard: Seq[Seq[KeyboardButton]],
  resizeKeyboard: Option[Boolean],
  oneTimeKeyboard: Option[Boolean],
  inputFieldPlaceholder: Option[String],
  selective: Option[Boolean]
) extends Markup
