package com.github.aalbul.zio.telegram.bot

import com.github.aalbul.zio.telegram.command.*
import com.github.aalbul.zio.telegram.domain.{InputMedia, Update}
import com.github.aalbul.zio.telegram.engine.BotEngine
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
}
