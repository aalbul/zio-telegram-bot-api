package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}

object ChatAdministratorRights {
  implicit val chatAdministratorRightsJsonCodec: JsonValueCodec[ChatAdministratorRights] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[ChatAdministratorRights]] object
    * @param isAnonymous
    *   True, if the user's presence in the chat is hidden
    * @param canManageChat
    *   True, if the administrator can access the chat event log, chat statistics, message statistics in channels, see
    *   channel members, see anonymous administrators in supergroups and ignore slow mode. Implied by any other
    *   administrator privilege
    * @param canDeleteMessages
    *   True, if the administrator can delete messages of other users
    * @param canManageVideoChats
    *   True, if the administrator can manage video chats
    * @param canRestrictMembers
    *   True, if the administrator can restrict, ban or unban chat members
    * @param canPromoteMembers
    *   True, if the administrator can add new administrators with a subset of their own privileges or demote
    *   administrators that he has promoted, directly or indirectly (promoted by administrators that were appointed by
    *   the user)
    * @param canChangeInfo
    *   True, if the user is allowed to change the chat title, photo and other settings
    * @param canInviteUsers
    *   True, if the user is allowed to invite new users to the chat
    * @return
    *   [[ChatAdministratorRights]] builder
    */
  def of(
    isAnonymous: Boolean,
    canManageChat: Boolean,
    canDeleteMessages: Boolean,
    canManageVideoChats: Boolean,
    canRestrictMembers: Boolean,
    canPromoteMembers: Boolean,
    canChangeInfo: Boolean,
    canInviteUsers: Boolean
  ): ChatAdministratorRights = ChatAdministratorRights(
    isAnonymous = isAnonymous,
    canManageChat = canManageChat,
    canDeleteMessages = canDeleteMessages,
    canManageVideoChats = canManageVideoChats,
    canRestrictMembers = canRestrictMembers,
    canPromoteMembers = canPromoteMembers,
    canChangeInfo = canChangeInfo,
    canInviteUsers = canInviteUsers,
    canPostMessages = None,
    canEditMessages = None,
    canPinMessages = None,
    canManageTopics = None
  )
}

/** Represents the rights of an administrator in a chat.
  */
case class ChatAdministratorRights(
  isAnonymous: Boolean,
  canManageChat: Boolean,
  canDeleteMessages: Boolean,
  canManageVideoChats: Boolean,
  canRestrictMembers: Boolean,
  canPromoteMembers: Boolean,
  canChangeInfo: Boolean,
  canInviteUsers: Boolean,
  canPostMessages: Option[Boolean],
  canEditMessages: Option[Boolean],
  canPinMessages: Option[Boolean],
  canManageTopics: Option[Boolean]
) {

  /** True, if the administrator can post in the channel; channels only
    */
  def withCanPostMessages(canPostMessages: Boolean): ChatAdministratorRights =
    copy(canPostMessages = Some(canPostMessages))

  /** True, if the administrator can edit messages of other users and can pin messages; channels only
    */
  def withCanEditMessages(canEditMessages: Boolean): ChatAdministratorRights =
    copy(canEditMessages = Some(canEditMessages))

  /** True, if the user is allowed to pin messages; groups and supergroups only
    */
  def withCanPinMessages(canPinMessages: Boolean): ChatAdministratorRights = copy(canPinMessages = Some(canPinMessages))

  /** True, if the user is allowed to create, rename, close, and reopen forum topics; supergroups only
    */
  def withCanManageTopics(canManageTopics: Boolean): ChatAdministratorRights =
    copy(canManageTopics = Some(canManageTopics))
}
