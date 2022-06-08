package com.github.aalbul.zio.telegram.bot

import com.github.aalbul.zio.telegram.command.*
import zio.{ULayer, ZLayer}

object DefaultBot {
  def layer: ULayer[Bot] = ZLayer.succeed(new DefaultBot)
}

class DefaultBot extends Bot {
  override def getMe: GetMe = new GetMe
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
}
