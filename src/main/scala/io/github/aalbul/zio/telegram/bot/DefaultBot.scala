package io.github.aalbul.zio.telegram.bot

import io.github.aalbul.zio.telegram.command.*
import io.github.aalbul.zio.telegram.domain.ChatActions.ChatAction
import io.github.aalbul.zio.telegram.domain.{InputMedia, Update}
import io.github.aalbul.zio.telegram.engine.BotEngine
import zio.stream.ZStream
import zio.{ULayer, ZLayer}

object DefaultBot {
  val layer: ULayer[Bot] = ZLayer.succeed(new DefaultBot)
}

class DefaultBot extends Bot {
  override def getMe: GetMe = new GetMe
  override def streamUpdates(chunkSize: Long = 100L): ZStream[BotEngine, Throwable, Update] = StreamUpdates(chunkSize)
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
  override def getUserProfilePhotos(userId: String): GetUserProfilePhotos = GetUserProfilePhotos.of(userId)
}
