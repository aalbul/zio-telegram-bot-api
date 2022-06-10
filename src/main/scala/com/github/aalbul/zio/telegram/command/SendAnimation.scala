package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import com.github.aalbul.zio.telegram.domain.ParseModes.ParseMode
import com.github.aalbul.zio.telegram.domain.{Markup, Message, MessageEntity}

object SendAnimation {
  def of(chatId: String, animation: FileDescriptor): SendAnimation = SendAnimation(
    chatId = chatId,
    animation = animation,
    duration = None,
    width = None,
    height = None,
    thumb = None,
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

case class SendAnimation(
  chatId: String,
  animation: FileDescriptor,
  duration: Option[Long],
  width: Option[Long],
  height: Option[Long],
  thumb: Option[FileDescriptor],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  disableNotification: Option[Boolean],
  protectContent: Option[Boolean],
  replyToMessageId: Option[Long],
  allowSendingWithoutReply: Option[Boolean],
  replyMarkup: Option[Markup]
) extends Command[Message] {
  override val name: String = "sendAnimation"

  override def parameters: ApiParameters = MultipartBody.ofOpt(
    Some(stringPart("chat_id", chatId)),
    Some(animation.asMultipart("animation")),
    duration.map(stringPart("duration", _)),
    width.map(stringPart("width", _)),
    height.map(stringPart("height", _)),
    thumb.map(_.asMultipart("thumb")),
    caption.map(stringPart("caption", _)),
    parseMode.map(mode => stringPart("parse_mode", mode.toString)),
    captionEntities.map(entities => stringPart("caption_entities", JsonBody(entities).toJson)),
    disableNotification.map(stringPart("disable_notification", _)),
    protectContent.map(stringPart("protect_content", _)),
    replyToMessageId.map(stringPart("reply_to_message_id", _)),
    allowSendingWithoutReply.map(stringPart("allow_sending_without_reply", _)),
    replyMarkup.map(markup => stringPart("reply_markup", JsonBody(markup).toJson))
  )

  def withDuration(duration: Long): SendAnimation = copy(duration = Some(duration))
  def withWidth(width: Long): SendAnimation = copy(width = Some(width))
  def withHeight(height: Long): SendAnimation = copy(height = Some(height))
  def withThumb(thumb: FileDescriptor): SendAnimation = copy(thumb = Some(thumb))
  def withCaption(caption: String): SendAnimation = copy(caption = Some(caption))
  def withParseMode(parseMode: ParseMode): SendAnimation = copy(parseMode = Some(parseMode))
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): SendAnimation =
    copy(captionEntities = Some(captionEntities))
  def withDisableNotification(disable: Boolean): SendAnimation = copy(disableNotification = Some(disable))
  def withProtectContent(protect: Boolean): SendAnimation = copy(protectContent = Some(protect))
  def withReplyToMessageId(id: Int): SendAnimation = copy(replyToMessageId = Some(id))
  def withAllowSendingWithoutReply(allow: Boolean): SendAnimation = copy(allowSendingWithoutReply = Some(allow))
  def withReplyMarkup(markup: Markup): SendAnimation = copy(replyMarkup = Some(markup))
}
