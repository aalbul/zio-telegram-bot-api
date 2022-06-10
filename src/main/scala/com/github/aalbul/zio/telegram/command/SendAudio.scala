package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import com.github.aalbul.zio.telegram.domain.ParseModes.ParseMode
import com.github.aalbul.zio.telegram.domain.{Markup, Message, MessageEntity}

object SendAudio {
  def of(chatId: String, audio: FileDescriptor): SendAudio = SendAudio(
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

case class SendAudio(
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
) extends Command[Message] {
  override val name: String = "sendAudio"

  override def parameters: ApiParameters = MultipartBody.ofOpt(
    Some(stringPart("chat_id", chatId)),
    Some(audio.asMultipart("audio")),
    caption.map(stringPart("caption", _)),
    parseMode.map(mode => stringPart("parse_mode", mode.toString)),
    captionEntities.map(entities => stringPart("caption_entities", JsonBody(entities).toJson)),
    duration.map(stringPart("duration", _)),
    performer.map(stringPart("performer", _)),
    title.map(stringPart("title", _)),
    thumb.map(_.asMultipart("thumb")),
    disableNotification.map(stringPart("disable_notification", _)),
    protectContent.map(stringPart("protect_content", _)),
    replyToMessageId.map(stringPart("reply_to_message_id", _)),
    allowSendingWithoutReply.map(stringPart("allow_sending_without_reply", _)),
    replyMarkup.map(markup => stringPart("reply_markup", JsonBody(markup).toJson))
  )

  def withCaption(caption: String): SendAudio = copy(caption = Some(caption))
  def withParseMode(parseMode: ParseMode): SendAudio = copy(parseMode = Some(parseMode))
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): SendAudio =
    copy(captionEntities = Some(captionEntities))
  def withDuration(duration: Long): SendAudio = copy(duration = Some(duration))
  def withPerformer(performer: String): SendAudio = copy(performer = Some(performer))
  def withTitle(title: String): SendAudio = copy(title = Some(title))
  def withThumb(thumb: FileDescriptor): SendAudio = copy(thumb = Some(thumb))
  def withDisableNotification(disable: Boolean): SendAudio = copy(disableNotification = Some(disable))
  def withProtectContent(protect: Boolean): SendAudio = copy(protectContent = Some(protect))
  def withReplyToMessageId(id: Int): SendAudio = copy(replyToMessageId = Some(id))
  def withAllowSendingWithoutReply(allow: Boolean): SendAudio = copy(allowSendingWithoutReply = Some(allow))
  def withReplyMarkup(markup: Markup): SendAudio = copy(replyMarkup = Some(markup))
}
