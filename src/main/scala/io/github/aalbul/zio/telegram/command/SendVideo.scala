package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.*

import scala.concurrent.duration.Duration

object SendVideo {

  /** Constructs minimal [[SendVideo]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param video
    *   Video to send. Pass a file_id as String to send a video that exists on the Telegram servers (recommended), pass
    *   an HTTP URL as a String for Telegram to get a video from the Internet, or upload a new video using
    *   multipart/form-data. [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    * @return
    *   [[SendVideo]] builder
    */
  def of(chatId: String, video: FileDescriptor): SendVideo = new SendVideo(
    chatId = chatId,
    messageThreadId = None,
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

/** Use this method to send video files, Telegram clients support MPEG4 videos (other formats may be sent as
  * [[Document]]). On success, the sent [[Message]] is returned. Bots can currently send video files of up to 50 MB in
  * size, this limit may be changed in the future.
  */
case class SendVideo(
  chatId: String,
  messageThreadId: Option[Long],
  video: FileDescriptor,
  duration: Option[Duration],
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
    messageThreadId.map(stringPart("message_thread_id", _)),
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

  /** Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
    */
  def withMessageThreadId(messageThreadId: Long): SendVideo = copy(messageThreadId = Some(messageThreadId))

  /** Duration of sent video in seconds
    */
  def withDuration(duration: Duration): SendVideo = copy(duration = Some(duration))

  /** Video width
    */
  def withWidth(width: Long): SendVideo = copy(width = Some(width))

  /** Video height
    */
  def withHeight(height: Long): SendVideo = copy(height = Some(height))

  /** Thumbnail of the file sent; can be ignored if thumbnail generation for the file is supported server-side. The
    * thumbnail should be in JPEG format and less than 200 kB in size. A thumbnail's width and height should not exceed
    * 320. Ignored if the file is not uploaded using multipart/form-data. Thumbnails can't be reused and can be only
    * uploaded as a new file, so you can pass “attach://<file_attach_name>” if the thumbnail was uploaded using
    * multipart/form-data under <file_attach_name>.
    * [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    */
  def withThumb(thumb: FileDescriptor): SendVideo = copy(thumb = Some(thumb))

  /** Video caption (may also be used when resending videos by file_id), 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): SendVideo = copy(caption = Some(caption))

  /** Mode for parsing entities in the video caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): SendVideo = copy(parseMode = Some(parseMode))

  /** A JSON-serialized list of special entities that appear in the new caption, which can be specified instead of
    * ''parse_mode''
    */
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): SendVideo =
    copy(captionEntities = Some(captionEntities))

  /** Pass ''True'', if the uploaded video is suitable for streaming
    */
  def withSupportsStreaming(supports: Boolean): SendVideo = copy(supportsStreaming = Some(supports))

  /** Sends the message [[https://telegram.org/blog/channels-2-0#silent-messages silently]]. Users will receive a
    * notification with no sound.
    */
  def withDisableNotification(disable: Boolean): SendVideo = copy(disableNotification = Some(disable))

  /** Protects the contents of the sent message from forwarding and saving
    */
  def withProtectContent(protect: Boolean): SendVideo = copy(protectContent = Some(protect))

  /** If the message is a reply, ID of the original message
    */
  def withReplyToMessageId(id: Long): SendVideo = copy(replyToMessageId = Some(id))

  /** Pass ''True'', if the message should be sent even if the specified replied-to message is not found
    */
  def withAllowSendingWithoutReply(allow: Boolean): SendVideo = copy(allowSendingWithoutReply = Some(allow))

  /** Additional interface options. An object for an
    * [[https://core.telegram.org/bots#inline-keyboards-and-on-the-fly-updating inline keyboard]],
    * [[https://core.telegram.org/bots#keyboards custom reply keyboard]], instructions to remove reply keyboard or to
    * force a reply from the user.
    */
  def withReplyMarkup(markup: Markup): SendVideo = copy(replyMarkup = Some(markup))
}
