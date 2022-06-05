package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.MessageEntityTypes.MessageEntityType
import com.github.aalbul.zio.telegram.domain.User.UserOps
import com.pengrad.telegrambot.model.MessageEntity as LibMessageEntity

object MessageEntity {
  implicit class MessageEntityOps(entity: LibMessageEntity) {
    def asScala: MessageEntity = MessageEntity(
      `type` = MessageEntityTypes.byName(entity.`type`().name()),
      offset = entity.offset(),
      length = entity.length(),
      url = Option(entity.url()),
      user = Option(entity.user()).map(_.asScala),
      language = Option(entity.language())
    )
  }
}

case class MessageEntity(
  `type`: MessageEntityType,
  offset: Int,
  length: Int,
  url: Option[String],
  user: Option[User],
  language: Option[String]
)
