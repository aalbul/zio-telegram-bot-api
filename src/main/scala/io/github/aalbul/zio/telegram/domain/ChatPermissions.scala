package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ChatPermissions {
  implicit val chatPermissionsJsonCodec: JsonValueCodec[ChatPermissions] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[ChatPermissions]] object
    * @return
    *   [[ChatPermissions]] builder
    */
  def of: ChatPermissions = ChatPermissions(
    canSendMessages = None,
    canSendMediaMessages = None,
    canSendPolls = None,
    canSendOtherMessages = None,
    canAddWebPagePreviews = None,
    canChangeInfo = None,
    canInviteUsers = None,
    canPinMessages = None,
    canManageTopics = None
  )
}

/** Describes actions that a non-administrator user is allowed to take in a chat.
  */
case class ChatPermissions(
  canSendMessages: Option[Boolean],
  canSendMediaMessages: Option[Boolean],
  canSendPolls: Option[Boolean],
  canSendOtherMessages: Option[Boolean],
  canAddWebPagePreviews: Option[Boolean],
  canChangeInfo: Option[Boolean],
  canInviteUsers: Option[Boolean],
  canPinMessages: Option[Boolean],
  canManageTopics: Option[Boolean]
) {

  /** True, if the user is allowed to send text messages, contacts, locations and venues
    */
  def withCanSendMessages(canSendMessages: Boolean): ChatPermissions = copy(canSendMessages = Some(canSendMessages))

  /** True, if the user is allowed to send audios, documents, photos, videos, video notes and voice notes, implies
    * can_send_messages
    */
  def withCanSendMediaMessages(canSendMediaMessages: Boolean): ChatPermissions =
    copy(canSendMediaMessages = Some(canSendMediaMessages))

  /** True, if the user is allowed to send polls, implies can_send_messages
    */
  def withCanSendPolls(canSendPolls: Boolean): ChatPermissions = copy(canSendPolls = Some(canSendPolls))

  /** True, if the user is allowed to send animations, games, stickers and use inline bots, implies
    * can_send_media_messages
    */
  def withCanSendOtherMessages(canSendOtherMessages: Boolean): ChatPermissions =
    copy(canSendOtherMessages = Some(canSendOtherMessages))

  /** True, if the user is allowed to add web page previews to their messages, implies can_send_media_messages
    */
  def withCanAddWebPagePreviews(canAddWebPagePreviews: Boolean): ChatPermissions =
    copy(canAddWebPagePreviews = Some(canAddWebPagePreviews))

  /** True, if the user is allowed to change the chat title, photo and other settings. Ignored in public supergroups
    */
  def withCanChangeInfo(canChangeInfo: Boolean): ChatPermissions = copy(canChangeInfo = Some(canChangeInfo))

  /** True, if the user is allowed to invite new users to the chat
    */
  def withCanInviteUsers(canInviteUsers: Boolean): ChatPermissions = copy(canInviteUsers = Some(canInviteUsers))

  /** True, if the user is allowed to pin messages. Ignored in public supergroups
    */
  def withCanPinMessages(canPinMessages: Boolean): ChatPermissions = copy(canPinMessages = Some(canPinMessages))

  /** True, if the user is allowed to create forum topics. If omitted defaults to the value of can_pin_messages
    */
  def withCanManageTopics(canManageTopics: Boolean): ChatPermissions = copy(canManageTopics = Some(canManageTopics))
}
