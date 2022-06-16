package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.ParseModes.ParseMode
import io.github.aalbul.zio.telegram.domain.{Markup, Message, MessageEntity}

object SendVideo {
  def of(chatId: String, video: FileDescriptor): SendVideo = new SendVideo(
    chatId = chatId,
    video = video,
    duration = None,
    width = None,
    height = None,
    thumb = None,
    caption = None,
    parseMode = None,
    captionEntities = None,
    supportsStreaming = None,
    disableNotification = None,
    protectContent = None,
    replyToMessageId = None,
    allowSendingWithoutReply = None,
    replyMarkup = None
  )
}

case class SendVideo(
  chatId: String,
  video: FileDescriptor,
  duration: Option[Long],
  width: Option[Long],
  height: Option[Long],
  thumb: Option[FileDescriptor],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  supportsStreaming: Option[Boolean],
  disableNotification: Option[Boolean],
  protectContent: Option[Boolean],
  replyToMessageId: Option[Long],
  allowSendingWithoutReply: Option[Boolean],
  replyMarkup: Option[Markup]
) extends Command[Message] {
  override val name: String = "sendVideo"

  override def parameters: ApiParameters = MultipartBody.ofOpt(
    Some(stringPart("chat_id", chatId)),
    Some(video.asMultipart("video")),
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

  def withDuration(duration: Long): SendVideo = copy(duration = Some(duration))
  def withWidth(width: Long): SendVideo = copy(width = Some(width))
  def withHeight(height: Long): SendVideo = copy(height = Some(height))
  def withThumb(thumb: FileDescriptor): SendVideo = copy(thumb = Some(thumb))
  def withCaption(caption: String): SendVideo = copy(caption = Some(caption))
  def withParseMode(parseMode: ParseMode): SendVideo = copy(parseMode = Some(parseMode))
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): SendVideo =
    copy(captionEntities = Some(captionEntities))
  def withSupportsStreaming(supports: Boolean): SendVideo = copy(supportsStreaming = Some(supports))
  def withDisableNotification(disable: Boolean): SendVideo = copy(disableNotification = Some(disable))
  def withProtectContent(protect: Boolean): SendVideo = copy(protectContent = Some(protect))
  def withReplyToMessageId(id: Long): SendVideo = copy(replyToMessageId = Some(id))
  def withAllowSendingWithoutReply(allow: Boolean): SendVideo = copy(allowSendingWithoutReply = Some(allow))
  def withReplyMarkup(markup: Markup): SendVideo = copy(replyMarkup = Some(markup))
}
