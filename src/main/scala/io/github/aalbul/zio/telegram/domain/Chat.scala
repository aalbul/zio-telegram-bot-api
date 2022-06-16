package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.ChatTypes.ChatType
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object Chat {
  def of(id: Long, `type`: ChatType): Chat = Chat(
    id = id,
    `type` = `type`,
    title = None,
    username = None,
    firstName = None,
    lastName = None,
    photo = None,
    bio = None,
    hasPrivateForwards = None,
    description = None,
    inviteLink = None,
    pinnedMessage = None,
    permissions = None,
    slowModeDelay = None,
    messageAutoDeleteTime = None,
    hasProtectedContent = None,
    stickerSetName = None,
    canSetStickerSet = None,
    linkedChatId = None,
    location = None
  )
}

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
