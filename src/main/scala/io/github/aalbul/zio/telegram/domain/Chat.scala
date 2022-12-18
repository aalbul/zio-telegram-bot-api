package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object Chat {
  def of(id: Long, `type`: ChatType): Chat = Chat(
    id = id,
    `type` = `type`,
    title = None,
    username = None,
    firstName = None,
    lastName = None,
    isForum = None,
    photo = None,
    activeUsernames = None,
    emojiStatusCustomEmojiId = None,
    bio = None,
    hasPrivateForwards = None,
    hasRestrictedVoiceAndVideoMessages = None,
    joinToSendMessages = None,
    joinByRequest = None,
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

  implicit val chatJsonCodec: JsonValueCodec[Chat] = JsonCodecMaker.make(
    CodecMakerConfig
      .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
      .withAllowRecursiveTypes(true)
  )
}

case class Chat(
  id: Long,
  `type`: ChatType,
  title: Option[String],
  username: Option[String],
  firstName: Option[String],
  lastName: Option[String],
  isForum: Option[Boolean],
  photo: Option[ChatPhoto],
  activeUsernames: Option[Seq[String]],
  emojiStatusCustomEmojiId: Option[String],
  bio: Option[String],
  hasPrivateForwards: Option[Boolean],
  hasRestrictedVoiceAndVideoMessages: Option[Boolean],
  joinToSendMessages: Option[Boolean],
  joinByRequest: Option[Boolean],
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
