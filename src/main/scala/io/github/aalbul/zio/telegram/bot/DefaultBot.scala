package io.github.aalbul.zio.telegram.bot

import io.github.aalbul.zio.telegram.command.*
import io.github.aalbul.zio.telegram.domain.*
import io.github.aalbul.zio.telegram.engine.BotEngine
import io.github.aalbul.zio.telegram.projection.ProjectionBuilder
import zio.stream.ZStream
import zio.{ULayer, ZLayer}

object DefaultBot {
  val layer: ULayer[Bot] = ZLayer.succeed(new DefaultBot)
}

class DefaultBot extends Bot {
  override def getMe: GetMe = new GetMe
  override def logOut: LogOut = new LogOut
  override def close: Close = new Close
  override def streamUpdates(
    chunkSize: Long = 100L,
    allowedTypes: Set[UpdateType]
  ): ZStream[BotEngine, Throwable, Update] = StreamUpdates(chunkSize, allowedTypes)
  override def project: ProjectionBuilder = ProjectionBuilder.empty
  override def sendMessage(chatId: String, text: String): SendMessage = SendMessage.of(chatId, text)
  override def forwardMessage(messageId: Long, fromChatId: String, toChatId: String): ForwardMessage =
    ForwardMessage.of(messageId, fromChatId, toChatId)
  override def copyMessage(chatId: String, fromChatId: String, messageId: String): CopyMessage =
    CopyMessage.of(chatId, fromChatId, messageId)
  override def sendPhoto(chatId: String, photo: FileDescriptor): SendPhoto = SendPhoto.of(chatId, photo)
  override def sendAudio(chatId: String, audio: FileDescriptor): SendAudio = SendAudio.of(chatId, audio)
  override def sendDocument(chatId: String, document: FileDescriptor): SendDocument = SendDocument.of(chatId, document)
  override def sendVideo(chatId: String, video: FileDescriptor): SendVideo = SendVideo.of(chatId, video)
  override def sendAnimation(chatId: String, animation: FileDescriptor): SendAnimation =
    SendAnimation.of(chatId, animation)
  override def sendVoice(chatId: String, voice: FileDescriptor): SendVoice = SendVoice.of(chatId, voice)
  override def sendVideoNote(chatId: String, voiceNote: FileDescriptor): SendVideoNote =
    SendVideoNote.of(chatId, voiceNote)
  override def sendMediaGroup(chatId: String, media: Seq[InputMedia]): SendMediaGroup = SendMediaGroup.of(chatId, media)
  override def sendLocation(chatId: String, latitude: Double, longitude: Double): SendLocation =
    SendLocation.of(chatId, latitude, longitude)
  override def editMessageLiveLocation(latitude: Double, longitude: Double): EditMessageLiveLocation =
    EditMessageLiveLocation.of(latitude, longitude)
  override def stopMessageLiveLocation: StopMessageLiveLocation = StopMessageLiveLocation.of
  override def sendVenue(
    chatId: String,
    latitude: Double,
    longitude: Double,
    title: String,
    address: String
  ): SendVenue = SendVenue.of(chatId, latitude, longitude, title, address)
  override def sendContact(chatId: String, phoneNumber: String, firstName: String): SendContact =
    SendContact.of(chatId, phoneNumber, firstName)
  override def sendPoll(chatId: String, question: String, options: Seq[String]): SendPoll =
    SendPoll.of(chatId, question, options)
  override def sendDice(chatId: String): SendDice = SendDice.of(chatId)
  override def sendChatAction(chatId: String, action: ChatAction): SendChatAction = SendChatAction.of(chatId, action)
  override def getUserProfilePhotos(userId: Long): GetUserProfilePhotos = GetUserProfilePhotos.of(userId)
  override def getFile(fileId: String): GetFile = GetFile.of(fileId)
  override def banChatMember(chatId: String, userId: Long): BanChatMember = BanChatMember.of(chatId, userId)
  override def unbanChatMember(chatId: String, userId: Long): UnbanChatMember = UnbanChatMember.of(chatId, userId)
  override def restrictChatMember(chatId: String, userId: Long, permissions: ChatPermissions): RestrictChatMember =
    RestrictChatMember.of(chatId, userId, permissions)
  override def promoteChatMember(chatId: String, userId: Long): PromoteChatMember =
    PromoteChatMember.of(chatId, userId)
  override def setChatAdministratorCustomTitle(
    chatId: String,
    userId: Long,
    customTitle: String
  ): SetChatAdministratorCustomTitle =
    SetChatAdministratorCustomTitle.of(chatId, userId, customTitle)
  override def banChatSenderChat(chatId: String, senderChatId: Long): BanChatSenderChat =
    BanChatSenderChat.of(chatId, senderChatId)
  override def unbanChatSenderChat(chatId: String, senderChatId: Long): UnbanChatSenderChat =
    UnbanChatSenderChat.of(chatId, senderChatId)
  override def setChatPermissions(chatId: String, permissions: ChatPermissions): SetChatPermissions =
    SetChatPermissions.of(chatId, permissions)
  override def exportChatInviteLink(chatId: String): ExportChatInviteLink = ExportChatInviteLink.of(chatId)
  override def createChatInviteLink(chatId: String): CreateChatInviteLink = CreateChatInviteLink.of(chatId)
  override def editChatInviteLink(chatId: String, inviteLink: String): EditChatInviteLink =
    EditChatInviteLink.of(chatId, inviteLink)
  override def revokeChatInviteLink(chatId: String, inviteLink: String): RevokeChatInviteLink =
    RevokeChatInviteLink.of(chatId, inviteLink)
  override def getChat(chatId: String): GetChat = GetChat.of(chatId)
}
