package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.ParseModes.ParseMode
import io.github.aalbul.zio.telegram.domain.{Markup, Message, MessageEntity}

object SendAudio {

  /** Constructs minimal [[SendAudio]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param audio
    *   Audio file to send. Pass a file_id as String to send an audio file that exists on the Telegram servers
    *   (recommended), pass an HTTP URL as a String for Telegram to get an audio file from the Internet, or upload a new
    *   one using multipart/form-data.
    *   [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    * @return
    *   [[SendAudio]] builder
    */
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

/** Use this method to send audio files, if you want Telegram clients to display them in the music player. Your audio
  * must be in the .MP3 or .M4A format. On success, the sent [[Message]] is returned. Bots can currently send audio
  * files of up to 50 MB in size, this limit may be changed in the future.
  *
  * For sending voice messages, use the [[SendVoice]] method instead.
  */
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

  /** Audio caption, 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): SendAudio = copy(caption = Some(caption))

  /** Mode for parsing entities in the new caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): SendAudio = copy(parseMode = Some(parseMode))

  /** A JSON-serialized list of special entities that appear in the new caption, which can be specified instead of
    * ''parse_mode''
    */
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): SendAudio =
    copy(captionEntities = Some(captionEntities))

  /** Duration of the audio in seconds
    */
  def withDuration(duration: Long): SendAudio = copy(duration = Some(duration))

  /** Performer
    */
  def withPerformer(performer: String): SendAudio = copy(performer = Some(performer))

  /** Track name
    */
  def withTitle(title: String): SendAudio = copy(title = Some(title))

  /** Thumbnail of the file sent; can be ignored if thumbnail generation for the file is supported server-side. The
    * thumbnail should be in JPEG format and less than 200 kB in size. A thumbnail's width and height should not exceed
    * 320. Ignored if the file is not uploaded using multipart/form-data. Thumbnails can't be reused and can be only
    * uploaded as a new file, so you can pass “attach://<file_attach_name>” if the thumbnail was uploaded using
    * multipart/form-data under <file_attach_name>.
    * [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    */
  def withThumb(thumb: FileDescriptor): SendAudio = copy(thumb = Some(thumb))

  /** Sends the message [[https://telegram.org/blog/channels-2-0#silent-messages silently]]. Users will receive a
    * notification with no sound.
    */
  def withDisableNotification(disable: Boolean): SendAudio = copy(disableNotification = Some(disable))

  /** Protects the contents of the sent message from forwarding and saving
    */
  def withProtectContent(protect: Boolean): SendAudio = copy(protectContent = Some(protect))

  /** If the message is a reply, ID of the original message
    */
  def withReplyToMessageId(id: Long): SendAudio = copy(replyToMessageId = Some(id))

  /** Pass ''True'', if the message should be sent even if the specified replied-to message is not found
    */
  def withAllowSendingWithoutReply(allow: Boolean): SendAudio = copy(allowSendingWithoutReply = Some(allow))

  /** Additional interface options. An object for an
    * [[https://core.telegram.org/bots#inline-keyboards-and-on-the-fly-updating inline keyboard]],
    * [[https://core.telegram.org/bots#keyboards custom reply keyboard]], instructions to remove reply keyboard or to
    * force a reply from the user.
    */
  def withReplyMarkup(markup: Markup): SendAudio = copy(replyMarkup = Some(markup))
}
