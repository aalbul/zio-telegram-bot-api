package com.github.aalbul.zio.telegram.domain

object MessageEntityTypes extends Enumeration {
  type MessageEntityType = Value

  val Mention, Hashtag, Cashtag, BotCommand, Url, Email, PhoneNumber, Bold, Italic, Underline, Strikethrough, Spoiler,
    Code, Pre, TextLink, TextMention = Value

  private lazy val indexed = values
    .map(value => value.toString.split("(?<=.)(?=\\p{Lu})").map(_.toLowerCase).mkString("_") -> value).toMap

  def byName(name: String): MessageEntityType = indexed(name)
}
