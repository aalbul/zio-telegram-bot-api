package com.github.aalbul.zio.telegram.domain

import com.pengrad.telegrambot.model.User as LibUser

object User {
  implicit class UserOps(user: LibUser) {
    def asScala: User = User(
      id = user.id(),
      isBot = user.isBot,
      firstName = user.firstName(),
      lastName = Option(user.lastName()),
      username = Option(user.username()),
      languageCode = Option(user.languageCode()),
      canJoinGroups = Option(user.canJoinGroups()),
      canReadAllGroupMessages = Option(user.canReadAllGroupMessages()),
      supportsInlineQueries = Option(user.supportsInlineQueries())
    )
  }
}

case class User(
  id: Long,
  isBot: Boolean,
  firstName: String,
  lastName: Option[String],
  username: Option[String],
  languageCode: Option[String],
  canJoinGroups: Option[Boolean],
  canReadAllGroupMessages: Option[Boolean],
  supportsInlineQueries: Option[Boolean]
)
