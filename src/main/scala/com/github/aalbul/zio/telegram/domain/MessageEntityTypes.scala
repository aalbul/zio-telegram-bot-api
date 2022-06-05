package com.github.aalbul.zio.telegram.domain

import io.circe.{Decoder, Encoder}

object MessageEntityTypes extends Enumeration {
  type MessageEntityType = Value

  val Mention, Hashtag, Cashtag, BotCommand, Url, Email, PhoneNumber, Bold, Italic, Underline, Strikethrough, Spoiler,
    Code, Pre, TextLink, TextMention = Value

  private lazy val indexed = values
    .map(value => value.toString.split("(?<=.)(?=\\p{Lu})").map(_.toLowerCase).mkString("_") -> value)
    .toMap
  private lazy val reverseIndex = indexed.toSeq.map(_.swap).toMap

  def byName(name: String): MessageEntityType = indexed(name)

  implicit lazy val messageEntityTypeDecoder: Decoder[MessageEntityType] = Decoder.decodeString.map(byName)
  implicit lazy val messageEntityTypeEncoder: Encoder[MessageEntityType] = Encoder.encodeString.contramap(reverseIndex)
}
