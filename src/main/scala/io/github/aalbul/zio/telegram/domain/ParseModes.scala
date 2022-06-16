package io.github.aalbul.zio.telegram.domain

import io.circe.Encoder

object ParseModes extends Enumeration {
  implicit val parseModeEncoder: Encoder[ParseMode] = Encoder.encodeString.contramap(_.toString)
  type ParseMode = Value

  val Markdown, MarkdownV2, HTML = Value
}
