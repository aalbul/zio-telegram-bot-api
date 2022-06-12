package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import com.github.aalbul.zio.telegram.domain.ParseModes.ParseMode
import com.github.aalbul.zio.telegram.domain.{Markup, Message, MessageEntity}

object SendVoice {
  def of(chatId: String, voice: FileDescriptor): SendVoice = SendVoice(
    chatId = chatId,
    voice = voice,
    caption = None,
    parseMode = None,
    captionEntities = None,
    duration = None,
    disableNotification = None,
    protectContent = None,
    replyToMessageId = None,
    allowSendingWithoutReply = None,
    replyMarkup = None
  )
}

case class SendVoice(
  chatId: String,
  voice: FileDescriptor,
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  duration: Option[Long],
  disableNotification: Option[Boolean],
  protectContent: Option[Boolean],
  replyToMessageId: Option[Long],
  allowSendingWithoutReply: Option[Boolean],
  replyMarkup: Option[Markup]
) extends Command[Message] {
  override val name: String = "sendVoice"

  override def parameters: ApiParameters = MultipartBody.ofOpt(
    Some(stringPart("chat_id", chatId)),
    Some(voice.asMultipart("voice")),
    caption.map(stringPart("caption", _)),
    parseMode.map(mode => stringPart("parse_mode", mode.toString)),
    captionEntities.map(entities => stringPart("caption_entities", JsonBody(entities).toJson)),
    duration.map(stringPart("duration", _)),
    disableNotification.map(stringPart("disable_notification", _)),
    protectContent.map(stringPart("protect_content", _)),
    replyToMessageId.map(stringPart("reply_to_message_id", _)),
    allowSendingWithoutReply.map(stringPart("allow_sending_without_reply", _)),
    replyMarkup.map(markup => stringPart("reply_markup", JsonBody(markup).toJson))
  )

  def withCaption(caption: String): SendVoice = copy(caption = Some(caption))
  def withParseMode(parseMode: ParseMode): SendVoice = copy(parseMode = Some(parseMode))
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): SendVoice =
    copy(captionEntities = Some(captionEntities))
  def withDuration(duration: Long): SendVoice = copy(duration = Some(duration))
  def withDisableNotification(disable: Boolean): SendVoice = copy(disableNotification = Some(disable))
  def withProtectContent(protect: Boolean): SendVoice = copy(protectContent = Some(protect))
  def withReplyToMessageId(id: Long): SendVoice = copy(replyToMessageId = Some(id))
  def withAllowSendingWithoutReply(allow: Boolean): SendVoice = copy(allowSendingWithoutReply = Some(allow))
  def withReplyMarkup(markup: Markup): SendVoice = copy(replyMarkup = Some(markup))
}
