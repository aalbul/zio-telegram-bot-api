package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.ChatLocation.ChatLocationOps
import com.github.aalbul.zio.telegram.domain.ChatPermissions.ChatPermissionsOps
import com.github.aalbul.zio.telegram.domain.ChatPhoto.ChatPhotoOps
import com.github.aalbul.zio.telegram.domain.ChatTypes.ChatType
import com.github.aalbul.zio.telegram.domain.Message.MessageOps
import com.pengrad.telegrambot.model.Chat as LibChat

object Chat {
  implicit class ChatOps(chat: LibChat) {
    def asScala: Chat = Chat(
      id = chat.id(),
      `type` = ChatTypes.byName(chat.`type`().name()),
      title = Option(chat.title()),
      username = Option(chat.username()),
      firstName = Option(chat.firstName()),
      lastName = Option(chat.lastName()),
      photo = Option(chat.photo()).map(_.asScala),
      bio = Option(chat.bio()),
      hasPrivateForwards = Option(chat.hasPrivateForwards),
      description = Option(chat.description()),
      inviteLink = Option(chat.inviteLink()),
      pinnedMessage = Option(chat.pinnedMessage()).map(_.asScala),
      permissions = Option(chat.permissions()).map(_.asScala),
      slowModeDelay = Option(chat.slowModeDelay()),
      messageAutoDeleteTime = Option(chat.messageAutoDeleteTime()),
      hasProtectedContent = Option(chat.hasProtectedContent),
      stickerSetName = Option(chat.stickerSetName()),
      canSetStickerSet = Option(chat.canSetStickerSet),
      linkedChatId = Option(chat.linkedChatId()),
      location = Option(chat.location()).map(_.asScala)
    )
  }
}

case class Chat(
  id: Long,
  `type`: ChatType,
  title: Option[String],
  username: Option[String],
  firstName: Option[String],
  lastName: Option[String],
  photo: Option[ChatPhoto],
  bio: Option[String],
  hasPrivateForwards: Option[Boolean],
  description: Option[String],
  inviteLink: Option[String],
  pinnedMessage: Option[Message],
  permissions: Option[ChatPermissions],
  slowModeDelay: Option[Int],
  messageAutoDeleteTime: Option[Int],
  hasProtectedContent: Option[Boolean],
  stickerSetName: Option[String],
  canSetStickerSet: Option[Boolean],
  linkedChatId: Option[Long],
  location: Option[ChatLocation]
)
