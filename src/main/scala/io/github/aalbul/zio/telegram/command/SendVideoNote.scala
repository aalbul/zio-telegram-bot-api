package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.{Markup, Message}

import scala.concurrent.duration.Duration

object SendVideoNote {

  /** Constructs minimal [[SendVideoNote]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param videoNote
    *   Video note to send. Pass a file_id as String to send a video note that exists on the Telegram servers
    *   (recommended) or upload a new video using multipart/form-data.
    *   [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]. Sending video notes by
    *   a URL is currently unsupported
    * @return
    *   [[SendVideoNote]] builder
    */
  def of(chatId: String, videoNote: FileDescriptor): SendVideoNote = SendVideoNote(
    chatId = chatId,
    messageThreadId = None,
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

/** As of [[https://telegram.org/blog/video-messages-and-telescope v.4.0]], Telegram clients support rounded square
  * MPEG4 videos of up to 1 minute long. Use this method to send video messages. On success, the sent [[Message]] is
  * returned.
  */
case class SendVideoNote(
  chatId: String,
  messageThreadId: Option[Long],
  videoNote: FileDescriptor,
  duration: Option[Duration],
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
    messageThreadId.map(stringPart("message_thread_id", _)),
    Some(videoNote.asMultipart("video_note")),
    duration.map(stringPart("duration", _)),
    length.map(stringPart("length", _)),
    thumb.map(_.asMultipart("thumb")),
    disableNotification.map(stringPart("disable_notification", _)),
    protectContent.map(stringPart("protect_content", _)),
    replyToMessageId.map(stringPart("reply_to_message_id", _)),
    allowSendingWithoutReply.map(stringPart("allow_sending_without_reply", _)),
    replyMarkup.map(stringPart("reply_markup", _))
  )

  /** Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
    */
  def withMessageThreadId(messageThreadId: Long): SendVideoNote = copy(messageThreadId = Some(messageThreadId))

  /** Duration of sent video in seconds
    */
  def withDuration(duration: Duration): SendVideoNote = copy(duration = Some(duration))

  /** Video width and height, i.e. diameter of the video message
    */
  def withLength(length: Long): SendVideoNote = copy(length = Some(length))

  /** Thumbnail of the file sent; can be ignored if thumbnail generation for the file is supported server-side. The
    * thumbnail should be in JPEG format and less than 200 kB in size. A thumbnail's width and height should not exceed
    * 320. Ignored if the file is not uploaded using multipart/form-data. Thumbnails can't be reused and can be only
    * uploaded as a new file, so you can pass “attach://<file_attach_name>” if the thumbnail was uploaded using
    * multipart/form-data under <file_attach_name>.
    * [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    */
  def withThumb(thumb: FileDescriptor): SendVideoNote = copy(thumb = Some(thumb))

  /** Sends the message [[https://telegram.org/blog/channels-2-0#silent-messages silently]]. Users will receive a
    * notification with no sound.
    */
  def withDisableNotification(disable: Boolean): SendVideoNote = copy(disableNotification = Some(disable))

  /** Protects the contents of the sent message from forwarding and saving
    */
  def withProtectContent(protect: Boolean): SendVideoNote = copy(protectContent = Some(protect))

  /** If the message is a reply, ID of the original message
    */
  def withReplyToMessageId(id: Long): SendVideoNote = copy(replyToMessageId = Some(id))

  /** Pass ''True'', if the message should be sent even if the specified replied-to message is not found
    */
  def withAllowSendingWithoutReply(allow: Boolean): SendVideoNote = copy(allowSendingWithoutReply = Some(allow))

  /** Additional interface options. An object for an
    * [[https://core.telegram.org/bots#inline-keyboards-and-on-the-fly-updating inline keyboard]],
    * [[https://core.telegram.org/bots#keyboards custom reply keyboard]], instructions to remove reply keyboard or to
    * force a reply from the user.
    */
  def withReplyMarkup(markup: Markup): SendVideoNote = copy(replyMarkup = Some(markup))
}
