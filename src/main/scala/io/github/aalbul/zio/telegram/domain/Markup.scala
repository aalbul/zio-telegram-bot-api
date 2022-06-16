package io.github.aalbul.zio.telegram.domain

import io.circe.Encoder
import io.circe.syntax.EncoderOps

object Markup {
  implicit val markupEncoder: Encoder[Markup] = Encoder.instance {
    case markup: InlineKeyboardMarkup => markup.asJson
    case markup: ReplyKeyboardMarkup  => markup.asJson
    case markup: ReplyKeyboardRemove  => markup.asJson
    case markup: ForceReply           => markup.asJson
  }
}

trait Markup
