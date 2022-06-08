package com.github.aalbul.zio.telegram.bot

import com.github.aalbul.zio.telegram.command.*

trait Bot {
  def getMe: GetMe
  def sendMessage(chatId: String, text: String): SendMessage
  def forwardMessage(messageId: Long, fromChatId: String, toChatId: String): ForwardMessage
  def copyMessage(chatId: String, fromChatId: String, messageId: String): CopyMessage
  def sendPhoto(chatId: String, photo: FileDescriptor): SendPhoto
  def sendAudio(chatId: String, audio: FileDescriptor): SendAudio
  def sendDocument(chatId: String, document: FileDescriptor): SendDocument
  def sendVideo(chatId: String, video: FileDescriptor): SendVideo
  def sendAnimation(chatId: String, animation: FileDescriptor): SendAnimation
}
