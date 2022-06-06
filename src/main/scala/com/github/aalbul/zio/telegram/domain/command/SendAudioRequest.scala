package com.github.aalbul.zio.telegram.domain.command

import com.github.aalbul.zio.telegram.domain.ParseModes.ParseMode
import com.github.aalbul.zio.telegram.domain.{Markup, MessageEntity}

object SendAudioRequest {
  def forChat(chatId: String, audio: FileDescriptor): SendAudioRequest = SendAudioRequest(
    chatId = chatId,
    audio = audio,
    caption = None,
    parseMode = None,
    captionEntities = None,
    duration = None,
    performer = None,
    title = None,
    thumb = None,
    disableNotification = None,
    protectContent = None,
    replyToMessageId = None,
    allowSendingWithoutReply = None,
    replyMarkup = None
  )
}

case class SendAudioRequest(
  chatId: String,
  audio: FileDescriptor,
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  duration: Option[Long],
  performer: Option[String],
  title: Option[String],
  thumb: Option[FileDescriptor],
  disableNotification: Option[Boolean],
  protectContent: Option[Boolean],
  replyToMessageId: Option[Long],
  allowSendingWithoutReply: Option[Boolean],
  replyMarkup: Option[Markup]
)
