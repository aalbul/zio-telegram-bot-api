package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{named, CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ChatMember {
  implicit val chatMemberJsonCodec: JsonValueCodec[ChatMember] = JsonCodecMaker.make(
    CodecMakerConfig
      .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
      .withDiscriminatorFieldName(Some("status"))
  )
}

sealed trait ChatMember

object ChatMemberAdministrator {

  /** Constructs minimal [[ChatMemberAdministrator]] object
    * @param user
    *   Information about the user
    * @param canBeEdited
    *   True, if the bot is allowed to edit administrator privileges of that user
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
    *   [[ChatMemberAdministrator]] builder
    */
  def of(
    user: User,
    canBeEdited: Boolean,
    isAnonymous: Boolean,
    canManageChat: Boolean,
    canDeleteMessages: Boolean,
    canManageVideoChats: Boolean,
    canRestrictMembers: Boolean,
    canPromoteMembers: Boolean,
    canChangeInfo: Boolean,
    canInviteUsers: Boolean
  ): ChatMemberAdministrator = ChatMemberAdministrator(
    user = user,
    canBeEdited = canBeEdited,
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
    canManageTopics = None,
    customerTitle = None
  )
}

/** Represents a [[https://core.telegram.org/bots/api#chatmember chat member]] that has some additional privileges.
  */
@named("administrator")
case class ChatMemberAdministrator(
  user: User,
  canBeEdited: Boolean,
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
  canManageTopics: Option[Boolean],
  customerTitle: Option[String]
) extends ChatMember {

  /** True, if the administrator can post in the channel; channels only
    */
  def withCanPostMessages(canPostMessages: Boolean): ChatMemberAdministrator =
    copy(canPostMessages = Some(canPostMessages))

  /** True, if the administrator can edit messages of other users and can pin messages; channels only
    */
  def withCanEditMessages(canEditMessages: Boolean): ChatMemberAdministrator =
    copy(canEditMessages = Some(canEditMessages))

  /** True, if the user is allowed to pin messages; groups and supergroups only
    */
  def withCanPinMessages(canPinMessages: Boolean): ChatMemberAdministrator = copy(canPinMessages = Some(canPinMessages))

  /** True, if the user is allowed to create, rename, close, and reopen forum topics; supergroups only
    */
  def withCanManageTopics(canManageTopics: Boolean): ChatMemberAdministrator =
    copy(canManageTopics = Some(canManageTopics))

  /** Custom title for this user
    */
  def withCustomerTitle(customerTitle: String): ChatMemberAdministrator = copy(customerTitle = Some(customerTitle))
}

object ChatMemberBanned {

  /** Constructs minimal [[ChatMemberBanned]] object
    * @param user
    *   Information about the user
    * @param untilDate
    *   Date when restrictions will be lifted for this user; unix time. If 0, then the user is banned forever
    * @return
    *   [[ChatMemberBanned]] builder
    */
  def of(user: User, untilDate: Int): ChatMemberBanned = ChatMemberBanned(user = user, untilDate = untilDate)
}

/** Represents a [[https://core.telegram.org/bots/api#chatmember chat member]] that was banned in the chat and can't
  * return to the chat or view chat messages.
  */
@named("kicked")
case class ChatMemberBanned(user: User, untilDate: Int) extends ChatMember

object ChatMemberLeft {

  /** Constructs minimal [[ChatMemberLeft]] object
    * @param user
    *   Information about the user
    * @return
    *   [[ChatMemberLeft]] builder
    */
  def of(user: User): ChatMemberLeft = ChatMemberLeft(user = user)
}

/** Represents a [[https://core.telegram.org/bots/api#chatmember chat member]] that isn't currently a member of the
  * chat, but may join it themselves.
  */
@named("left")
case class ChatMemberLeft(user: User) extends ChatMember

object ChatMemberMember {

  /** Constructs minimal [[ChatMemberMember]] object
    *
    * @param user
    *   Information about the user
    * @return
    *   [[ChatMemberMember]] builder
    */
  def of(user: User): ChatMemberMember = ChatMemberMember(user = user)
}

/** Represents a [[https://core.telegram.org/bots/api#chatmember chat member]] that has no additional privileges or
  * restrictions.
  */
@named("member")
case class ChatMemberMember(user: User) extends ChatMember

object ChatMemberOwner {

  /** Constructs minimal [[ChatMemberOwner]] object
    *
    * @param user
    *   Information about the user
    * @param isAnonymous
    *   True, if the user's presence in the chat is hidden
    *
    * @return
    *   [[ChatMemberOwner]] builder
    */
  def of(user: User, isAnonymous: Boolean): ChatMemberOwner = ChatMemberOwner(
    user = user,
    isAnonymous = isAnonymous,
    customTitle = None
  )
}

/** Represents a [[https://core.telegram.org/bots/api#chatmember chat member]] that owns the chat and has all
  * administrator privileges.
  */
@named("creator")
case class ChatMemberOwner(user: User, isAnonymous: Boolean, customTitle: Option[String]) extends ChatMember {

  /** Custom title for this user
    */
  def withCustomTitle(customTitle: String): ChatMemberOwner = copy(customTitle = Some(customTitle))
}

object ChatMemberRestricted {

  /** Constructs minimal [[ChatMemberRestricted]] object
    *
    * @param user
    *   Information about the user
    * @param isMember
    *   True, if the user is a member of the chat at the moment of the request
    * @param canChangeInfo
    *   True, if the user is allowed to change the chat title, photo and other settings
    * @param canInviteUsers
    *   True, if the user is allowed to invite new users to the chat
    * @param canPinMembers
    *   True, if the user is allowed to pin messages
    * @param canManageTopics
    *   True, if the user is allowed to create forum topics
    * @param canSendMessages
    *   True, if the user is allowed to send text messages, contacts, locations and venues
    * @param canSendMediaMessages
    *   True, if the user is allowed to send audios, documents, photos, videos, video notes and voice notes
    * @param canSendPolls
    *   True, if the user is allowed to send polls
    * @param canSendOtherMessages
    *   True, if the user is allowed to send animations, games, stickers and use inline bots
    * @param canAddWebPagePreviews
    *   True, if the user is allowed to add web page previews to their messages
    * @param untilDate
    *   Date when restrictions will be lifted for this user; unix time. If 0, then the user is restricted forever
    * @return
    *   [[ChatMemberRestricted]] builder
    */
  def of(
    user: User,
    isMember: Boolean,
    canChangeInfo: Boolean,
    canInviteUsers: Boolean,
    canPinMembers: Boolean,
    canManageTopics: Boolean,
    canSendMessages: Boolean,
    canSendMediaMessages: Boolean,
    canSendPolls: Boolean,
    canSendOtherMessages: Boolean,
    canAddWebPagePreviews: Boolean,
    untilDate: Int
  ): ChatMemberRestricted = ChatMemberRestricted(
    user = user,
    isMember = isMember,
    canChangeInfo = canChangeInfo,
    canInviteUsers = canInviteUsers,
    canPinMembers = canPinMembers,
    canManageTopics = canManageTopics,
    canSendMessages = canSendMessages,
    canSendMediaMessages = canSendMediaMessages,
    canSendPolls = canSendPolls,
    canSendOtherMessages = canSendOtherMessages,
    canAddWebPagePreviews = canAddWebPagePreviews,
    untilDate = untilDate
  )
}

/** Represents a [[https://core.telegram.org/bots/api#chatmember chat member]] that is under certain restrictions in the
  * chat. Supergroups only.
  */
@named("restricted")
case class ChatMemberRestricted(
  user: User,
  isMember: Boolean,
  canChangeInfo: Boolean,
  canInviteUsers: Boolean,
  canPinMembers: Boolean,
  canManageTopics: Boolean,
  canSendMessages: Boolean,
  canSendMediaMessages: Boolean,
  canSendPolls: Boolean,
  canSendOtherMessages: Boolean,
  canAddWebPagePreviews: Boolean,
  untilDate: Int
) extends ChatMember
