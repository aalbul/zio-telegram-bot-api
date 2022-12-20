package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object Chat {
  implicit val chatJsonCodec: JsonValueCodec[Chat] = JsonCodecMaker.make(
    CodecMakerConfig
      .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
      .withAllowRecursiveTypes(true)
  )

  /** Constructs minimal [[Chat]] object
    * @param id
    *   Unique identifier for this chat. This number may have more than 32 significant bits and some programming
    *   languages may have difficulty/silent defects in interpreting it. But it has at most 52 significant bits, so a
    *   signed 64-bit integer or double-precision float type are safe for storing this identifier.
    * @param `type`
    *   Type of chat, can be either “private”, “group”, “supergroup” or “channel”
    * @return
    *   [[Chat]] builder
    */
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
}

/** This object represents a chat.
  */
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
  slowModeDelay: Option[Long],
  messageAutoDeleteTime: Option[Long],
  hasProtectedContent: Option[Boolean],
  stickerSetName: Option[String],
  canSetStickerSet: Option[Boolean],
  linkedChatId: Option[Long],
  location: Option[ChatLocation]
) {

  /** Title, for supergroups, channels and group chats
    */
  def withTitle(title: String): Chat = copy(title = Some(title))

  /** Username, for private chats, supergroups and channels if available
    */
  def withUsername(username: String): Chat = copy(username = Some(username))

  /** First name of the other party in a private chat
    */
  def withFirstName(firstName: String): Chat = copy(firstName = Some(firstName))

  /** Last name of the other party in a private chat
    */
  def withLastName(lastName: String): Chat = copy(lastName = Some(lastName))

  /** True, if the supergroup chat is a forum (has
    * [[https://telegram.org/blog/topics-in-groups-collectible-usernames#topics-in-groups topics]] enabled)
    */
  def withIsForum(isForum: Boolean): Chat = copy(isForum = Some(isForum))

  /** Chat photo. Returned only in [[https://core.telegram.org/bots/api#getchat getChat]].
    */
  def withPhoto(photo: ChatPhoto): Chat = copy(photo = Some(photo))

  /** If non-empty, the list of all
    * [[https://telegram.org/blog/topics-in-groups-collectible-usernames#collectible-usernames active chat usernames]];
    * for private chats, supergroups and channels. Returned only in
    * [[https://core.telegram.org/bots/api#getchat getChat]].
    */
  def withActiveUsernames(activeUsernames: Seq[String]): Chat = copy(activeUsernames = Some(activeUsernames))

  /** Custom emoji identifier of emoji status of the other party in a private chat. Returned only in
    * [[https://core.telegram.org/bots/api#getchat getChat]].
    */
  def withEmojiStatusCustomEmojiId(emojiStatusCustomEmojiId: String): Chat =
    copy(emojiStatusCustomEmojiId = Some(emojiStatusCustomEmojiId))

  /** Bio of the other party in a private chat. Returned only in [[https://core.telegram.org/bots/api#getchat getChat]].
    */
  def withBio(bio: String): Chat = copy(bio = Some(bio))

  /** True, if privacy settings of the other party in the private chat allows to use `tg://user?id=<user_id>` links only
    * in chats with the user. Returned only in [[https://core.telegram.org/bots/api#getchat getChat]].
    */
  def withHasPrivateForwards(hasPrivateForwards: Boolean): Chat = copy(hasPrivateForwards = Some(hasPrivateForwards))

  /** True, if the privacy settings of the other party restrict sending voice and video note messages in the private
    * chat. Returned only in [[https://core.telegram.org/bots/api#getchat getChat]].
    */
  def withHasRestrictedVoiceAndVideoMessages(hasRestrictedVoiceAndVideoMessages: Boolean): Chat =
    copy(hasRestrictedVoiceAndVideoMessages = Some(hasRestrictedVoiceAndVideoMessages))

  /** True, if users need to join the supergroup before they can send messages. Returned only in
    * [[https://core.telegram.org/bots/api#getchat getChat]].
    */
  def withJoinToSendMessages(joinToSendMessages: Boolean): Chat = copy(joinToSendMessages = Some(joinToSendMessages))

  /** True, if all users directly joining the supergroup need to be approved by supergroup administrators. Returned only
    * in [[https://core.telegram.org/bots/api#getchat getChat]].
    */
  def withJoinByRequest(joinByRequest: Boolean): Chat = copy(joinByRequest = Some(joinByRequest))

  /** Description, for groups, supergroups and channel chats. Returned only in
    * [[https://core.telegram.org/bots/api#getchat getChat]].
    */
  def withDescription(description: String): Chat = copy(description = Some(description))

  /** Primary invite link, for groups, supergroups and channel chats. Returned only in
    * [[https://core.telegram.org/bots/api#getchat getChat]].
    */
  def withInviteLink(inviteLink: String): Chat = copy(inviteLink = Some(inviteLink))

  /** The most recent pinned message (by sending date). Returned only in
    * [[https://core.telegram.org/bots/api#getchat getChat]].
    */
  def withPinnedMessage(pinnedMessage: Message): Chat = copy(pinnedMessage = Some(pinnedMessage))

  /** Default chat member permissions, for groups and supergroups. Returned only in
    * [[https://core.telegram.org/bots/api#getchat getChat]].
    */
  def withPermissions(permissions: ChatPermissions): Chat = copy(permissions = Some(permissions))

  /** For supergroups, the minimum allowed delay between consecutive messages sent by each unpriviledged user; in
    * seconds. Returned only in [[https://core.telegram.org/bots/api#getchat getChat]].
    */
  def withSlowModeDelay(slowModeDelay: Long): Chat = copy(slowModeDelay = Some(slowModeDelay))

  /** The time after which all messages sent to the chat will be automatically deleted; in seconds. Returned only in
    * [[https://core.telegram.org/bots/api#getchat getChat]].
    */
  def withMessageAutoDeleteTime(messageAutoDeleteTime: Long): Chat =
    copy(messageAutoDeleteTime = Some(messageAutoDeleteTime))

  /** True, if messages from the chat can't be forwarded to other chats. Returned only in
    * [[https://core.telegram.org/bots/api#getchat getChat]].
    */
  def withHasProtectedContent(hasProtectedContent: Boolean): Chat =
    copy(hasProtectedContent = Some(hasProtectedContent))

  /** For supergroups, name of group sticker set. Returned only in
    * [[https://core.telegram.org/bots/api#getchat getChat]].
    */
  def withStickerSetName(stickerSetName: String): Chat = copy(stickerSetName = Some(stickerSetName))

  /** True, if the bot can change the group sticker set. Returned only in
    * [[https://core.telegram.org/bots/api#getchat getChat]].
    */
  def withCanSetStickerSet(canSetStickerSet: Boolean): Chat = copy(canSetStickerSet = Some(canSetStickerSet))

  /** Unique identifier for the linked chat, i.e. the discussion group identifier for a channel and vice versa; for
    * supergroups and channel chats. This identifier may be greater than 32 bits and some programming languages may have
    * difficulty/silent defects in interpreting it. But it is smaller than 52 bits, so a signed 64 bit integer or
    * double-precision float type are safe for storing this identifier. Returned only in
    * [[https://core.telegram.org/bots/api#getchat getChat]].
    */
  def withLinkedChatId(linkedChatId: Long): Chat = copy(linkedChatId = Some(linkedChatId))

  /** For supergroups, the location to which the supergroup is connected. Returned only in
    * [[https://core.telegram.org/bots/api#getchat getChat]].
    */
  def withLocation(location: ChatLocation): Chat = copy(location = Some(location))
}
