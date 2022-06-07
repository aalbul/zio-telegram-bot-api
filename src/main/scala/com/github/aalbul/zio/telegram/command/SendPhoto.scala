package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import com.github.aalbul.zio.telegram.domain.ParseModes.ParseMode
import com.github.aalbul.zio.telegram.domain.{Markup, Message, MessageEntity}

object SendPhoto {
  def of(chatId: String, photo: FileDescriptor): SendPhoto = new SendPhoto(
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

case class SendPhoto(
  chatId: String,
  photo: FileDescriptor,
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  disableNotification: Option[Boolean],
  protectContent: Option[Boolean],
  replyToMessageId: Option[Long],
  allowSendingWithoutReply: Option[Boolean],
  replyMarkup: Option[Markup]
) extends Command[Message] {
  override val name: String = "sendPhoto"

  override def parameters: ApiParameters = MultipartBody.of(
    Some(stringPart("chat_id", chatId)),
    Some(photo.asMultipart("photo")),
    caption.map(stringPart("caption", _)),
    parseMode.map(mode => stringPart("parse_mode", mode.toString)),
    captionEntities.map(entities => stringPart("caption_entities", JsonBody(entities).toJson)),
    disableNotification.map(stringPart("disable_notification", _)),
    protectContent.map(stringPart("protect_content", _)),
    replyToMessageId.map(stringPart("reply_to_message_id", _)),
    allowSendingWithoutReply.map(stringPart("allow_sending_without_reply", _)),
    replyMarkup.map(markup => stringPart("reply_markup", JsonBody(markup).toJson))
  )

  def withCaption(caption: String): SendPhoto = copy(caption = Some(caption))
  def withParseMode(parseMode: ParseMode): SendPhoto = copy(parseMode = Some(parseMode))
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): SendPhoto =
    copy(captionEntities = Some(captionEntities))
  def withDisableNotification(disable: Boolean): SendPhoto = copy(disableNotification = Some(disable))
  def withProtectContent(protect: Boolean): SendPhoto = copy(protectContent = Some(protect))
  def withReplyToMessageId(id: Long): SendPhoto = copy(replyToMessageId = Some(id))
  def withAllowSendingWithoutReply(allow: Boolean): SendPhoto = copy(allowSendingWithoutReply = Some(allow))
  def withReplyMarkup(markup: Markup): SendPhoto = copy(replyMarkup = Some(markup))
}
