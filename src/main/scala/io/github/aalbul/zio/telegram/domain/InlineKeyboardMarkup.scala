package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec
case class InlineKeyboardMarkup(inlineKeyboard: Seq[Seq[InlineKeyboardButton]]) extends Markup
