package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.Animation.AnimationOps
import com.github.aalbul.zio.telegram.domain.Audio.AudioOps
import com.github.aalbul.zio.telegram.domain.Chat.ChatOps
import com.github.aalbul.zio.telegram.domain.Contact.ContactOps
import com.github.aalbul.zio.telegram.domain.Dice.DiceOps
import com.github.aalbul.zio.telegram.domain.Document.DocumentOps
import com.github.aalbul.zio.telegram.domain.Game.GameOps
import com.github.aalbul.zio.telegram.domain.InlineKeyboardMarkup.InlineKeyboardMarkupOps
import com.github.aalbul.zio.telegram.domain.Invoice.InvoiceOps
import com.github.aalbul.zio.telegram.domain.Location.LocationOps
import com.github.aalbul.zio.telegram.domain.MessageAutoDeleteTimerChanged.MessageAutoDeleteTimerChangedOps
import com.github.aalbul.zio.telegram.domain.MessageEntity.MessageEntityOps
import com.github.aalbul.zio.telegram.domain.PassportData.PassportDataOps
import com.github.aalbul.zio.telegram.domain.PhotoSize.PhotoSizeOps
import com.github.aalbul.zio.telegram.domain.Poll.PollOps
import com.github.aalbul.zio.telegram.domain.ProximityAlertTriggered.ProximityAlertTriggeredOps
import com.github.aalbul.zio.telegram.domain.Sticker.StickerOps
import com.github.aalbul.zio.telegram.domain.SuccessfulPayment.SuccessfulPaymentOps
import com.github.aalbul.zio.telegram.domain.User.UserOps
import com.github.aalbul.zio.telegram.domain.Venue.VenueOps
import com.github.aalbul.zio.telegram.domain.Video.VideoOps
import com.github.aalbul.zio.telegram.domain.VideoChatEnded.VideoChatEndedOps
import com.github.aalbul.zio.telegram.domain.VideoChatParticipantsInvited.VideoChatParticipantsInvitedOps
import com.github.aalbul.zio.telegram.domain.VideoChatScheduled.VideoChatScheduledOps
import com.github.aalbul.zio.telegram.domain.VideoNote.VideoNoteOps
import com.github.aalbul.zio.telegram.domain.Voice.VoiceOps
import com.github.aalbul.zio.telegram.domain.WebAppData.WebAppDataOps
import com.pengrad.telegrambot.model.Message as LibMessage

import java.time.Instant

object Message {
  implicit class MessageOps(message: LibMessage) {
    def asScala: Message = Message(
      messageId = message.messageId(),
      from = Option(message.from()).map(_.asScala),
      senderChat = Option(message.senderChat()).map(_.asScala),
      date = Instant.ofEpochSecond(message.date().toLong),
      chat = message.chat().asScala,
      forwardFrom = Option(message.forwardFrom()).map(_.asScala),
      forwardFromChat = Option(message.forwardFromChat()).map(_.asScala),
      forwardFromMessageId = Option(message.forwardFromMessageId()),
      forwardSignature = Option(message.forwardSignature()),
      forwardSenderName = Option(message.forwardSenderName()),
      forwardDate = Option(message.forwardDate()).map(date => Instant.ofEpochSecond(date.toLong)),
      isAutomaticForward = Option(message.isAutomaticForward),
      replyToMessage = Option(message.replyToMessage()).map(_.asScala),
      viaBot = Option(message.viaBot()).map(_.asScala),
      editDate = Option(message.editDate()).map(date => Instant.ofEpochSecond(date.toLong)),
      hasProtectedContent = Option(message.hasProtectedContent),
      mediaGroupId = Option(message.mediaGroupId()),
      authorSignature = Option(message.authorSignature()),
      text = Option(message.text()),
      entities = Option(message.entities()).map(_.map(_.asScala)),
      animation = Option(message.animation()).map(_.asScala),
      audio = Option(message.audio()).map(_.asScala),
      document = Option(message.document()).map(_.asScala),
      photo = Option(message.photo()).map(_.map(_.asScala)),
      sticker = Option(message.sticker()).map(_.asScala),
      video = Option(message.video()).map(_.asScala),
      videoNote = Option(message.videoNote()).map(_.asScala),
      voice = Option(message.voice()).map(_.asScala),
      caption = Option(message.caption()),
      captionEntities = Option(message.captionEntities()).map(_.map(_.asScala)),
      contact = Option(message.contact()).map(_.asScala),
      dice = Option(message.dice()).map(_.asScala),
      game = Option(message.game()).map(_.asScala),
      poll = Option(message.poll()).map(_.asScala),
      venue = Option(message.venue()).map(_.asScala),
      location = Option(message.location()).map(_.asScala),
      newChatMembers = Option(message.newChatMembers()).map(_.map(_.asScala)),
      leftChatMember = Option(message.leftChatMember()).map(_.asScala),
      newChatTitle = Option(message.newChatTitle()),
      newChatPhoto = Option(message.newChatPhoto()).map(_.map(_.asScala)),
      deleteChatPhoto = Option(message.deleteChatPhoto()),
      groupChatCreated = Option(message.groupChatCreated()),
      supergroupChatCreated = Option(message.supergroupChatCreated()),
      channelChatCreated = Option(message.channelChatCreated()),
      messageAutoDeleteTimerChanged = Option(message.messageAutoDeleteTimerChanged()).map(_.asScala),
      migrateToChatId = Option(message.migrateToChatId()),
      migrateFromChatId = Option(message.migrateFromChatId()),
      pinnedMessage = Option(message.pinnedMessage()).map(_.asScala),
      invoice = Option(message.invoice()).map(_.asScala),
      successfulPayment = Option(message.successfulPayment()).map(_.asScala),
      connectedWebsite = Option(message.connectedWebsite()),
      passportData = Option(message.passportData()).map(_.asScala),
      proximityAlertTriggered = Option(message.proximityAlertTriggered()).map(_.asScala),
      videoChatScheduled = Option(message.videoChatScheduled()).map(_.asScala),
      videoChatStarted = Option(message.videoChatStarted()).map(_ => VideoChatStarted),
      videoChatEnded = Option(message.videoChatEnded()).map(_.asScala),
      videoChatParticipantsInvited = Option(message.videoChatParticipantsInvited()).map(_.asScala),
      webAppData = Option(message.webAppData()).map(_.asScala),
      replyMarkup = Option(message.replyMarkup()).map(_.asScala)
    )
  }
}

case class Message(
  messageId: Int,
  from: Option[User],
  senderChat: Option[Chat],
  date: Instant,
  chat: Chat,
  forwardFrom: Option[User],
  forwardFromChat: Option[Chat],
  forwardFromMessageId: Option[Int],
  forwardSignature: Option[String],
  forwardSenderName: Option[String],
  forwardDate: Option[Instant],
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
  videoChatScheduled: Option[VideoChatScheduled],
  videoChatStarted: Option[VideoChatStarted.type],
  videoChatEnded: Option[VideoChatEnded],
  videoChatParticipantsInvited: Option[VideoChatParticipantsInvited],
  webAppData: Option[WebAppData],
  replyMarkup: Option[InlineKeyboardMarkup]
)
