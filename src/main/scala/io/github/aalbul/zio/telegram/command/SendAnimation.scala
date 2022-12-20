package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.{Markup, Message, MessageEntity, ParseMode}

object SendAnimation {

  /** Constructs minimal [[SendAnimation]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param animation
    *   Animation to send. Pass a file_id as String to send an animation that exists on the Telegram servers
    *   (recommended), pass an HTTP URL as a String for Telegram to get an animation from the Internet, or upload a new
    *   animation using multipart/form-data.
    *   [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    * @return
    *   [[SendAnimation]] builder
    */
  def of(chatId: String, animation: FileDescriptor): SendAnimation = SendAnimation(
    chatId = chatId,
    messageThreadId = None,
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

/** Use this method to send animation files (GIF or H.264/MPEG-4 AVC video without sound). On success, the sent
  * [[Message]] is returned. Bots can currently send animation files of up to 50 MB in size, this limit may be changed
  * in the future.
  */
case class SendAnimation(
  chatId: String,
  messageThreadId: Option[Long],
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
    messageThreadId.map(stringPart("message_thread_id", _)),
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

  /** Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
    */
  def withMessageThreadId(messageThreadId: Long): SendAnimation = copy(messageThreadId = Some(messageThreadId))

  /** Duration of sent animation in seconds
    */
  def withDuration(duration: Long): SendAnimation = copy(duration = Some(duration))

  /** Animation width
    */
  def withWidth(width: Long): SendAnimation = copy(width = Some(width))

  /** Animation height
    */
  def withHeight(height: Long): SendAnimation = copy(height = Some(height))

  /** Thumbnail of the file sent; can be ignored if thumbnail generation for the file is supported server-side. The
    * thumbnail should be in JPEG format and less than 200 kB in size. A thumbnail's width and height should not exceed
    * 320. Ignored if the file is not uploaded using multipart/form-data. Thumbnails can't be reused and can be only
    * uploaded as a new file, so you can pass “attach://<file_attach_name>” if the thumbnail was uploaded using
    * multipart/form-data under <file_attach_name>.
    * [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    */
  def withThumb(thumb: FileDescriptor): SendAnimation = copy(thumb = Some(thumb))

  /** Animation caption (may also be used when resending animation by file_id), 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): SendAnimation = copy(caption = Some(caption))

  /** Mode for parsing entities in the animation caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): SendAnimation = copy(parseMode = Some(parseMode))

  /** A JSON-serialized list of special entities that appear in the new caption, which can be specified instead of
    * ''parse_mode''
    */
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): SendAnimation =
    copy(captionEntities = Some(captionEntities))

  /** Sends the message [[https://telegram.org/blog/channels-2-0#silent-messages silently]]. Users will receive a
    * notification with no sound.
    */
  def withDisableNotification(disable: Boolean): SendAnimation = copy(disableNotification = Some(disable))

  /** Protects the contents of the sent message from forwarding and saving
    */
  def withProtectContent(protect: Boolean): SendAnimation = copy(protectContent = Some(protect))

  /** If the message is a reply, ID of the original message
    */
  def withReplyToMessageId(id: Int): SendAnimation = copy(replyToMessageId = Some(id))

  /** Pass ''True'', if the message should be sent even if the specified replied-to message is not found
    */
  def withAllowSendingWithoutReply(allow: Boolean): SendAnimation = copy(allowSendingWithoutReply = Some(allow))

  /** Additional interface options. An object for an
    * [[https://core.telegram.org/bots#inline-keyboards-and-on-the-fly-updating inline keyboard]],
    * [[https://core.telegram.org/bots#keyboards custom reply keyboard]], instructions to remove reply keyboard or to
    * force a reply from the user.
    */
  def withReplyMarkup(markup: Markup): SendAnimation = copy(replyMarkup = Some(markup))
}
