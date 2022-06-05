package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.ChatTypes.ChatType
import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
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
