package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import java.time.Instant

object Message {

  /** Constructs minimal [[Message]]
    * @param messageId
    *   Unique message identifier inside this chat
    * @param date
    *   Date the message was sent in Unix time
    * @param chat
    *   Conversation the message belongs to
    * @return
    *   [[Message]] builder
    */
  def of(messageId: Long, date: Instant, chat: Chat): Message = Message(
    messageId = messageId,
    messageThreadId = None,
    from = None,
    senderChat = None,
    date = date,
    chat = chat,
    forwardFrom = None,
    forwardFromChat = None,
    forwardFromMessageId = None,
    forwardSignature = None,
    forwardSenderName = None,
    forwardDate = None,
    isTopicMessage = None,
    isAutomaticForward = None,
    replyToMessage = None,
    viaBot = None,
    editDate = None,
    hasProtectedContent = None,
    mediaGroupId = None,
    authorSignature = None,
    text = None,
    entities = None,
    animation = None,
    audio = None,
    document = None,
    photo = None,
    sticker = None,
    video = None,
    videoNote = None,
    voice = None,
    caption = None,
    captionEntities = None,
    contact = None,
    dice = None,
    game = None,
    poll = None,
    venue = None,
    location = None,
    newChatMembers = None,
    leftChatMember = None,
    newChatTitle = None,
    newChatPhoto = None,
    deleteChatPhoto = None,
    groupChatCreated = None,
    supergroupChatCreated = None,
    channelChatCreated = None,
    messageAutoDeleteTimerChanged = None,
    migrateToChatId = None,
    migrateFromChatId = None,
    pinnedMessage = None,
    invoice = None,
    successfulPayment = None,
    connectedWebsite = None,
    passportData = None,
    proximityAlertTriggered = None,
    forumTopicCreated = None,
    forumTopicClosed = None,
    forumTopicReopened = None,
    videoChatScheduled = None,
    videoChatStarted = None,
    videoChatEnded = None,
    videoChatParticipantsInvited = None,
    webAppData = None,
    replyMarkup = None
  )

  implicit val messageJsonCodec: JsonValueCodec[Message] = JsonCodecMaker.make(
    CodecMakerConfig
      .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
      .withAllowRecursiveTypes(true)
  )

  implicit val seqMessageJsonCodec: JsonValueCodec[Seq[Message]] = JsonCodecMaker.make
}

/** This object represents a message. */
case class Message(
  messageId: Long,
  messageThreadId: Option[Long],
  from: Option[User],
  senderChat: Option[Chat],
  date: Instant,
  chat: Chat,
  forwardFrom: Option[User],
  forwardFromChat: Option[Chat],
  forwardFromMessageId: Option[Long],
  forwardSignature: Option[String],
  forwardSenderName: Option[String],
  forwardDate: Option[Instant],
  isTopicMessage: Option[Boolean],
  isAutomaticForward: Option[Boolean],
  replyToMessage: Option[Message],
  viaBot: Option[User],
  editDate: Option[Instant],
  hasProtectedContent: Option[Boolean],
  mediaGroupId: Option[String],
  authorSignature: Option[String],
  text: Option[String],
  entities: Option[Seq[MessageEntity]],
  animation: Option[Animation],
  audio: Option[Audio],
  document: Option[Document],
  photo: Option[Seq[PhotoSize]],
  sticker: Option[Sticker],
  video: Option[Video],
  videoNote: Option[VideoNote],
  voice: Option[Voice],
  caption: Option[String],
  captionEntities: Option[Seq[MessageEntity]],
  contact: Option[Contact],
  dice: Option[Dice],
  game: Option[Game],
  poll: Option[Poll],
  venue: Option[Venue],
  location: Option[Location],
  newChatMembers: Option[Seq[User]],
  leftChatMember: Option[User],
  newChatTitle: Option[String],
  newChatPhoto: Option[Seq[PhotoSize]],
  deleteChatPhoto: Option[Boolean],
  groupChatCreated: Option[Boolean],
  supergroupChatCreated: Option[Boolean],
  channelChatCreated: Option[Boolean],
  messageAutoDeleteTimerChanged: Option[MessageAutoDeleteTimerChanged],
  migrateToChatId: Option[Long],
  migrateFromChatId: Option[Long],
  pinnedMessage: Option[Message],
  invoice: Option[Invoice],
  successfulPayment: Option[SuccessfulPayment],
  connectedWebsite: Option[String],
  passportData: Option[PassportData],
  proximityAlertTriggered: Option[ProximityAlertTriggered],
  forumTopicCreated: Option[ForumTopicCreated],
  forumTopicClosed: Option[ForumTopicClosed],
  forumTopicReopened: Option[ForumTopicReopened],
  videoChatScheduled: Option[VideoChatScheduled],
  videoChatStarted: Option[VideoChatStarted],
  videoChatEnded: Option[VideoChatEnded],
  videoChatParticipantsInvited: Option[VideoChatParticipantsInvited],
  webAppData: Option[WebAppData],
  replyMarkup: Option[InlineKeyboardMarkup]
) {

  /** Unique identifier of a message thread to which the message belongs; for supergroups only
    */
  def withMessageThreadId(id: Long): Message = copy(messageThreadId = Some(id))

  /** Sender of the message; empty for messages sent to channels. For backward compatibility, the field contains a fake
    * sender user in non-channel chats, if the message was sent on behalf of a chat.
    */
  def withFrom(from: User): Message = copy(from = Some(from))

  /** Sender of the message, sent on behalf of a chat. For example, the channel itself for channel posts, the supergroup
    * itself for messages from anonymous group administrators, the linked channel for messages automatically forwarded
    * to the discussion group. For backward compatibility, the field from contains a fake sender user in non-channel
    * chats, if the message was sent on behalf of a chat.
    */
  def withSenderChat(chat: Chat): Message = copy(senderChat = Some(chat))

  /** For forwarded messages, sender of the original message */
  def withForwardFrom(user: User): Message = copy(forwardFrom = Some(user))

  /** For messages forwarded from channels or from anonymous administrators, information about the original sender chat
    */
  def withForwardFromChat(chat: Chat): Message = copy(forwardFromChat = Some(chat))

  /** For messages forwarded from channels, identifier of the original message in the channel */
  def withForwardFromMessageId(messageId: Long): Message = copy(forwardFromMessageId = Some(messageId))

  /** For forwarded messages that were originally sent in channels or by an anonymous chat administrator, signature of
    * the message sender if present
    */
  def withForwardSignature(signature: String): Message = copy(forwardSignature = Some(signature))

  /** Sender's name for messages forwarded from users who disallow adding a link to their account in forwarded messages
    */
  def withForwardSenderName(name: String): Message = copy(forwardSenderName = Some(name))

  /** For forwarded messages, date the original message was sent in Unix time */
  def withForwardDate(date: Instant): Message = copy(forwardDate = Some(date))

  /** True, if the message is sent to a forum topic
    */
  def withIsTopicMessage(isTopicMessage: Boolean): Message = copy(isTopicMessage = Some(isTopicMessage))

  /** True, if the message is a channel post that was automatically forwarded to the connected discussion group */
  def withIsAutomaticForward(isAutomatic: Boolean): Message = copy(isAutomaticForward = Some(isAutomatic))

  /** For replies, the original message. Note that the Message object in this field will not contain further
    * reply_to_message fields even if it itself is a reply.
    */
  def withReplyToMessage(message: Message): Message = copy(replyToMessage = Some(message))

  /** Bot through which the message was sent */
  def withViaBot(bot: User): Message = copy(viaBot = Some(bot))

  /** Date the message was last edited in Unix time */
  def withEditDate(date: Instant): Message = copy(editDate = Some(date))

  /** True, if the message can't be forwarded */
  def withHasProtectedContent(hasProtectedContent: Boolean): Message =
    copy(hasProtectedContent = Some(hasProtectedContent))

  /** The unique identifier of a media message group this message belongs to */
  def withMediaGroupId(groupId: String): Message = copy(mediaGroupId = Some(groupId))

  /** Signature of the post author for messages in channels, or the custom title of an anonymous group administrator */
  def withAuthorSignature(signature: String): Message = copy(authorSignature = Some(signature))

  /** For text messages, the actual UTF-8 text of the message */
  def withText(text: String): Message = copy(text = Some(text))

  /** For text messages, special entities like usernames, URLs, bot commands, etc. that appear in the text */
  def withEntities(entities: Seq[MessageEntity]): Message = copy(entities = Some(entities))

  /** Message is an animation, information about the animation. For backward compatibility, when this field is set, the
    * document field will also be set
    */
  def withAnimation(animation: Animation): Message = copy(animation = Some(animation))

  /** Message is an audio file, information about the file */
  def withAudio(audio: Audio): Message = copy(audio = Some(audio))

  /** Message is a general file, information about the file */
  def withDocument(document: Document): Message = copy(document = Some(document))

  /** Message is a photo, available sizes of the photo */
  def withPhoto(photo: Seq[PhotoSize]): Message = copy(photo = Some(photo))

  /** Message is a sticker, information about the sticker */
  def withSticker(sticker: Sticker): Message = copy(sticker = Some(sticker))

  /** Message is a video, information about the video */
  def withVideo(video: Video): Message = copy(video = Some(video))

  /** Message is a [[https://telegram.org/blog/video-messages-and-telescope video note]], information about the video
    * message
    */
  def withVideoNote(videoNote: VideoNote): Message = copy(videoNote = Some(videoNote))

  /** Message is a voice message, information about the file */
  def withVoice(voice: Voice): Message = copy(voice = Some(voice))

  /** Caption for the animation, audio, document, photo, video or voice */
  def withCaption(caption: String): Message = copy(caption = Some(caption))

  /** For messages with a caption, special entities like usernames, URLs, bot commands, etc. that appear in the caption
    */
  def withCaptionEntities(entities: Seq[MessageEntity]): Message = copy(captionEntities = Some(entities))

  /** Message is a shared contact, information about the contact */
  def withContact(contact: Contact): Message = copy(contact = Some(contact))

  /** Message is a dice with random value */
  def withDice(dice: Dice): Message = copy(dice = Some(dice))

  /** Message is a game, information about the game. [[https://core.telegram.org/bots/api#games More about games »]] */
  def withGame(game: Game): Message = copy(game = Some(game))

  /** Message is a native poll, information about the poll */
  def withPoll(poll: Poll): Message = copy(poll = Some(poll))

  /** Message is a venue, information about the venue. For backward compatibility, when this field is set, the location
    * field will also be set
    */
  def withVenue(venue: Venue): Message = copy(venue = Some(venue))

  /** Message is a shared location, information about the location */
  def withLocation(location: Location): Message = copy(location = Some(location))

  /** New members that were added to the group or supergroup and information about them (the bot itself may be one of
    * these members)
    */
  def withNewChatMembers(members: Seq[User]): Message = copy(newChatMembers = Some(members))

  /** A member was removed from the group, information about them (this member may be the bot itself) */
  def withLeftChatMember(member: User): Message = copy(leftChatMember = Some(member))

  /** A chat title was changed to this value */
  def withNewChatTitle(title: String): Message = copy(newChatTitle = Some(title))

  /** A chat photo was change to this value */
  def withNewChatPhoto(photo: Seq[PhotoSize]): Message = copy(newChatPhoto = Some(photo))

  /** Service message: the chat photo was deleted */
  def withDeleteChatPhoto(delete: Boolean): Message = copy(deleteChatPhoto = Some(delete))

  /** Service message: the group has been created */
  def withGroupChatCreated(created: Boolean): Message = copy(groupChatCreated = Some(created))

  /** Service message: the supergroup has been created. This field can't be received in a message coming through
    * updates, because bot can't be a member of a supergroup when it is created. It can only be found in
    * reply_to_message if someone replies to a very first message in a directly created supergroup.
    */
  def withSupergroupChatCreated(created: Boolean): Message = copy(supergroupChatCreated = Some(created))

  /** Service message: the channel has been created. This field can't be received in a message coming through updates,
    * because bot can't be a member of a channel when it is created. It can only be found in reply_to_message if someone
    * replies to a very first message in a channel.
    */
  def withChannelChatCreated(created: Boolean): Message = copy(channelChatCreated = Some(created))

  /** Service message: auto-delete timer settings changed in the chat */
  def withMessageAutoDeleteTimerChanged(changed: MessageAutoDeleteTimerChanged): Message =
    copy(messageAutoDeleteTimerChanged = Some(changed))

  /** The group has been migrated to a supergroup with the specified identifier. This number may have more than 32
    * significant bits and some programming languages may have difficulty/silent defects in interpreting it. But it has
    * at most 52 significant bits, so a signed 64-bit integer or double-precision float type are safe for storing this
    * identifier.
    */
  def withMigrateToChatId(chatId: Long): Message = copy(migrateToChatId = Some(chatId))

  /** The supergroup has been migrated from a group with the specified identifier. This number may have more than 32
    * significant bits and some programming languages may have difficulty/silent defects in interpreting it. But it has
    * at most 52 significant bits, so a signed 64-bit integer or double-precision float type are safe for storing this
    * identifier.
    */
  def withMigrateFromChatId(chatId: Long): Message = copy(migrateFromChatId = Some(chatId))

  /** Specified message was pinned. Note that the Message object in this field will not contain further reply_to_message
    * fields even if it is itself a reply.
    */
  def withPinnedMessage(message: Message): Message = copy(pinnedMessage = Some(message))

  /** Message is an invoice for a [[https://core.telegram.org/bots/api#payments payment]], information about the
    * invoice. [[https://core.telegram.org/bots/api#payments More about payments »]]
    */
  def withInvoice(invoice: Invoice): Message = copy(invoice = Some(invoice))

  /** Message is a service message about a successful payment, information about the payment.
    * [[https://core.telegram.org/bots/api#payments More about payments »]]
    */
  def withSuccessfulPayment(successfulPayment: SuccessfulPayment): Message =
    copy(successfulPayment = Some(successfulPayment))

  /** The domain name of the website on which the user has logged in.
    * [[https://core.telegram.org/widgets/login More about Telegram Login »]]
    */
  def withConnectedWebsite(website: String): Message = copy(connectedWebsite = Some(website))

  /** Telegram Passport data */
  def withPassportData(data: PassportData): Message = copy(passportData = Some(data))

  /** Service message. A user in the chat triggered another user's proximity alert while sharing Live Location. */
  def withProximityAlertTriggered(event: ProximityAlertTriggered): Message = copy(proximityAlertTriggered = Some(event))

  /** Service message: forum topic created */
  def withForumTopicCreated(forumTopicCreated: ForumTopicCreated): Message =
    copy(forumTopicCreated = Some(forumTopicCreated))

  /** Service message: forum topic closed */
  def withForumTopicClosed(forumTopicClosed: ForumTopicClosed): Message =
    copy(forumTopicClosed = Some(forumTopicClosed))

  /** Service message: forum topic reopened */
  def withForumTopicReopened(forumTopicReopened: ForumTopicReopened): Message =
    copy(forumTopicReopened = Some(forumTopicReopened))

  /** Service message: video chat scheduled */
  def withVideoChatScheduled(scheduled: VideoChatScheduled): Message = copy(videoChatScheduled = Some(scheduled))

  /** Service message: video chat started */
  def withVideoChatStarted(started: VideoChatStarted): Message = copy(videoChatStarted = Some(started))

  /** Service message: video chat ended */
  def withVideoChatEnded(ended: VideoChatEnded): Message = copy(videoChatEnded = Some(ended))

  /** Service message: new participants invited to a video chat */
  def withVideoChatParticipantsInvited(invited: VideoChatParticipantsInvited): Message =
    copy(videoChatParticipantsInvited = Some(invited))

  /** Service message: data sent by a Web App */
  def withWebAppData(data: WebAppData): Message = copy(webAppData = Some(data))

  /** Inline keyboard attached to the message. login_url buttons are represented as ordinary url buttons. */
  def withReplyMarkup(markup: InlineKeyboardMarkup): Message = copy(replyMarkup = Some(markup))
}
