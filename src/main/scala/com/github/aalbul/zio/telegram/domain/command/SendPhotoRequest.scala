package com.github.aalbul.zio.telegram.domain.command

import com.github.aalbul.zio.telegram.domain.ParseModes.ParseMode
import com.github.aalbul.zio.telegram.domain.{Markup, MessageEntity}

object SendPhotoRequest {
  def forChat(chatId: String, photo: FileDescriptor): SendPhotoRequest = SendPhotoRequest(
    chatId = chatId,
    photo = photo,
    caption = None,
    parseMode = None,
    captionEntities = None,
    disableNotification = None,
    protectContent = None,
    replyToMessageId = None,
    allowSendingWithoutReply = None,
    replyMarkup = None
  )
}

case class SendPhotoRequest(
  chatId: String,
  photo: FileDescriptor,
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  disableNotification: Option[Boolean],
  protectContent: Option[Boolean],
  replyToMessageId: Option[Int],
  allowSendingWithoutReply: Option[Boolean],
  replyMarkup: Option[Markup]
)
