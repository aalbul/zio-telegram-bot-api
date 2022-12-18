package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.PromoteChatMember.PromoteChatMemberPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import codecs.boolean

object PromoteChatMember {
  object PromoteChatMemberPayload {
    implicit val promoteChatMemberPayloadJsonCodec: JsonValueCodec[PromoteChatMemberPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class PromoteChatMemberPayload(
    chatId: String,
    userId: Long,
    isAnonymous: Option[Boolean],
    canManageChat: Option[Boolean],
    canPostMessages: Option[Boolean],
    canEditMessages: Option[Boolean],
    canDeleteMessages: Option[Boolean],
    canManageVideoChats: Option[Boolean],
    canRestrictMembers: Option[Boolean],
    canPromoteMembers: Option[Boolean],
    canChangeInfo: Option[Boolean],
    canInviteUsers: Option[Boolean],
    canPinMessages: Option[Boolean]
  )

  /** Constructs minimal [[PromoteChatMember]] command
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param userId
    *   Unique identifier of the target user
    * @return
    *   [[PromoteChatMember]] builder
    */
  def of(chatId: String, userId: Long): PromoteChatMember = PromoteChatMember(
    PromoteChatMemberPayload(
      chatId = chatId,
      userId = userId,
      isAnonymous = None,
      canManageChat = None,
      canPostMessages = None,
      canEditMessages = None,
      canDeleteMessages = None,
      canManageVideoChats = None,
      canRestrictMembers = None,
      canPromoteMembers = None,
      canChangeInfo = None,
      canInviteUsers = None,
      canPinMessages = None
    )
  )
}

/** Use this method to promote or demote a user in a supergroup or a channel. The bot must be an administrator in the
  * chat for this to work and must have the appropriate administrator rights. Pass ''False'' for all boolean parameters
  * to demote a user. Returns ''True'' on success.
  */
case class PromoteChatMember(payload: PromoteChatMemberPayload) extends Command[Boolean] {
  override val name: String = "promoteChatMember"

  override def parameters: ApiParameters = JsonBody(payload)

  /** Pass ''True'', if the administrator's presence in the chat is hidden
    */
  def withAsAnonymous(isAnonymous: Boolean): PromoteChatMember =
    copy(payload = payload.copy(isAnonymous = Some(isAnonymous)))

  /** Pass ''True'', if the administrator can access the chat event log, chat statistics, message statistics in
    * channels, see channel members, see anonymous administrators in supergroups and ignore slow mode. Implied by any
    * other administrator privilege
    */
  def withCanManageChat(canManageChat: Boolean): PromoteChatMember =
    copy(payload = payload.copy(canManageChat = Some(canManageChat)))

  /** Pass ''True'', if the administrator can create channel posts, channels only
    */
  def withCanPostMessages(canPostMessages: Boolean): PromoteChatMember =
    copy(payload = payload.copy(canPostMessages = Some(canPostMessages)))

  /** Pass ''True'', if the administrator can edit messages of other users and can pin messages, channels only
    */
  def withCanEditMessages(canEditMessages: Boolean): PromoteChatMember =
    copy(payload = payload.copy(canEditMessages = Some(canEditMessages)))

  /** Pass ''True'', if the administrator can delete messages of other users
    */
  def withCanDeleteMessages(canDeleteMessages: Boolean): PromoteChatMember =
    copy(payload = payload.copy(canDeleteMessages = Some(canDeleteMessages)))

  /** Pass ''True'', if the administrator can manage video chats
    */
  def withCanManageVideoChats(canManageVideoChats: Boolean): PromoteChatMember =
    copy(payload = payload.copy(canManageVideoChats = Some(canManageVideoChats)))

  /** Pass ''True'', if the administrator can restrict, ban or unban chat members
    */
  def withCanRestrictMembers(canRestrictMembers: Boolean): PromoteChatMember =
    copy(payload = payload.copy(canRestrictMembers = Some(canRestrictMembers)))

  /** Pass ''True'', if the administrator can add new administrators with a subset of their own privileges or demote
    * administrators that he has promoted, directly or indirectly (promoted by administrators that were appointed by
    * him)
    */
  def withCanPromoteMembers(canPromoteMembers: Boolean): PromoteChatMember =
    copy(payload = payload.copy(canPromoteMembers = Some(canPromoteMembers)))

  /** Pass ''True'', if the administrator can change chat title, photo and other settings
    */
  def withCanChangeInfo(canChangeInfo: Boolean): PromoteChatMember =
    copy(payload = payload.copy(canChangeInfo = Some(canChangeInfo)))

  /** Pass ''True'', if the administrator can invite new users to the chat
    */
  def withCanInviteUsers(canInviteUsers: Boolean): PromoteChatMember =
    copy(payload = payload.copy(canInviteUsers = Some(canInviteUsers)))

  /** Pass ''True'', if the administrator can pin messages, supergroups only
    */
  def withCanPinMessages(canPinMessages: Boolean): PromoteChatMember =
    copy(payload = payload.copy(canPinMessages = Some(canPinMessages)))
}
