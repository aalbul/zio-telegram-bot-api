package com.github.aalbul.zio.telegram.bot

import com.github.aalbul.zio.telegram.command.*
import com.github.aalbul.zio.telegram.domain.{InputMedia, Update}
import com.github.aalbul.zio.telegram.engine.BotEngine
import zio.stream.ZStream

trait Bot {
  def getMe: GetMe
  def streamUpdates(chunkSize: Long = 100L): ZStream[BotEngine, Throwable, Update]
  def sendMessage(chatId: String, text: String): SendMessage
  def forwardMessage(messageId: Long, fromChatId: String, toChatId: String): ForwardMessage
  def copyMessage(chatId: String, fromChatId: String, messageId: String): CopyMessage
  def sendPhoto(chatId: String, photo: FileDescriptor): SendPhoto
  def sendAudio(chatId: String, audio: FileDescriptor): SendAudio
  def sendDocument(chatId: String, document: FileDescriptor): SendDocument
  def sendVideo(chatId: String, video: FileDescriptor): SendVideo
  def sendAnimation(chatId: String, animation: FileDescriptor): SendAnimation
  def sendVoice(chatId: String, voice: FileDescriptor): SendVoice
  def sendVideoNote(chatId: String, voiceNote: FileDescriptor): SendVideoNote
  def sendMediaGroup(chatId: String, media: Seq[InputMedia]): SendMediaGroup
  def sendLocation(chatId: String, latitude: Double, longitude: Double): SendLocation
  def editMessageLiveLocation(latitude: Double, longitude: Double): EditMessageLiveLocation
  def stopMessageLiveLocation: StopMessageLiveLocation
  def sendVenue(chatId: String, latitude: Double, longitude: Double, title: String, address: String): SendVenue
  def sendContact(chatId: String, phoneNumber: String, firstName: String): SendContact
}
