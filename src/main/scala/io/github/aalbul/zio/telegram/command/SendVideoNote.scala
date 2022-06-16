package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.{Markup, Message}

object SendVideoNote {
  def of(chatId: String, videoNote: FileDescriptor): SendVideoNote = SendVideoNote(
    chatId = chatId,
    videoNote = videoNote,
    duration = None,
    length = None,
    thumb = None,
    disableNotification = None,
    protectContent = None,
    replyToMessageId = None,
    allowSendingWithoutReply = None,
    replyMarkup = None
  )
}

case class SendVideoNote(
  chatId: String,
  videoNote: FileDescriptor,
  duration: Option[Long],
  length: Option[Long],
  thumb: Option[FileDescriptor],
  disableNotification: Option[Boolean],
  protectContent: Option[Boolean],
  replyToMessageId: Option[Long],
  allowSendingWithoutReply: Option[Boolean],
  replyMarkup: Option[Markup]
) extends Command[Message] {
  override val name: String = "sendVideoNote"

  override def parameters: ApiParameters = MultipartBody.ofOpt(
    Some(stringPart("chat_id", chatId)),
    Some(videoNote.asMultipart("video_note")),
    duration.map(stringPart("duration", _)),
    length.map(stringPart("length", _)),
    thumb.map(_.asMultipart("thumb")),
    disableNotification.map(stringPart("disable_notification", _)),
    protectContent.map(stringPart("protect_content", _)),
    replyToMessageId.map(stringPart("reply_to_message_id", _)),
    allowSendingWithoutReply.map(stringPart("allow_sending_without_reply", _)),
    replyMarkup.map(markup => stringPart("reply_markup", JsonBody(markup).toJson))
  )

  def withDuration(duration: Long): SendVideoNote = copy(duration = Some(duration))
  def withLength(length: Long): SendVideoNote = copy(length = Some(length))
  def withThumb(thumb: FileDescriptor): SendVideoNote = copy(thumb = Some(thumb))
  def withDisableNotification(disable: Boolean): SendVideoNote = copy(disableNotification = Some(disable))
  def withProtectContent(protect: Boolean): SendVideoNote = copy(protectContent = Some(protect))
  def withReplyToMessageId(id: Long): SendVideoNote = copy(replyToMessageId = Some(id))
  def withAllowSendingWithoutReply(allow: Boolean): SendVideoNote = copy(allowSendingWithoutReply = Some(allow))
  def withReplyMarkup(markup: Markup): SendVideoNote = copy(replyMarkup = Some(markup))
}
