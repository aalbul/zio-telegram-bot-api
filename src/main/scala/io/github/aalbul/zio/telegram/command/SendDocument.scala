package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.{Markup, Message, MessageEntity, ParseMode}

object SendDocument {

  /** Constructs minimal [[SendDocument]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param document
    *   File to send. Pass a file_id as String to send a file that exists on the Telegram servers (recommended), pass an
    *   HTTP URL as a String for Telegram to get a file from the Internet, or upload a new one using
    *   multipart/form-data. [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    * @return
    *   [[SendDocument]] builder
    */
  def of(chatId: String, document: FileDescriptor): SendDocument = new SendDocument(
    chatId = chatId,
    messageThreadId = None,
    document = document,
    thumb = None,
    caption = None,
    parseMode = None,
    captionEntities = None,
    disableContentTypeDetection = None,
    disableNotification = None,
    protectContent = None,
    replyToMessageId = None,
    allowSendingWithoutReply = None,
    replyMarkup = None
  )
}

/** Use this method to send general files. On success, the sent [[Message]] is returned. Bots can currently send files
  * of any type of up to 50 MB in size, this limit may be changed in the future.
  */
case class SendDocument(
  chatId: String,
  messageThreadId: Option[Long],
  document: FileDescriptor,
  thumb: Option[FileDescriptor],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  disableContentTypeDetection: Option[Boolean],
  disableNotification: Option[Boolean],
  protectContent: Option[Boolean],
  replyToMessageId: Option[Long],
  allowSendingWithoutReply: Option[Boolean],
  replyMarkup: Option[Markup]
) extends Command[Message] {
  override val name: String = "sendDocument"

  override def parameters: ApiParameters = MultipartBody.ofOpt(
    Some(stringPart("chat_id", chatId)),
    messageThreadId.map(stringPart("message_thread_id", _)),
    Some(document.asMultipart("document")),
    thumb.map(_.asMultipart("thumb")),
    caption.map(stringPart("caption", _)),
    parseMode.map(mode => stringPart("parse_mode", mode.toString)),
    captionEntities.map(entities => stringPart("caption_entities", JsonBody(entities).toJson)),
    disableContentTypeDetection.map(stringPart("disable_content_type_detection", _)),
    disableNotification.map(stringPart("disable_notification", _)),
    protectContent.map(stringPart("protect_content", _)),
    replyToMessageId.map(stringPart("reply_to_message_id", _)),
    allowSendingWithoutReply.map(stringPart("allow_sending_without_reply", _)),
    replyMarkup.map(markup => stringPart("reply_markup", JsonBody(markup).toJson))
  )

  /** Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
    */
  def withMessageThreadId(messageThreadId: Long): SendDocument = copy(messageThreadId = Some(messageThreadId))

  /** Thumbnail of the file sent; can be ignored if thumbnail generation for the file is supported server-side. The
    * thumbnail should be in JPEG format and less than 200 kB in size. A thumbnail's width and height should not exceed
    * 320. Ignored if the file is not uploaded using multipart/form-data. Thumbnails can't be reused and can be only
    * uploaded as a new file, so you can pass “attach://<file_attach_name>” if the thumbnail was uploaded using
    * multipart/form-data under <file_attach_name>.
    * [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    */
  def withThumb(thumb: FileDescriptor): SendDocument = copy(thumb = Some(thumb))

  /** Document caption (may also be used when resending documents by file_id), 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): SendDocument = copy(caption = Some(caption))

  /** Mode for parsing entities in the document caption. See formatting options for more details.
    */
  def withParseMode(parseMode: ParseMode): SendDocument = copy(parseMode = Some(parseMode))

  /** A JSON-serialized list of special entities that appear in the new caption, which can be specified instead of
    * ''parse_mode''
    */
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): SendDocument =
    copy(captionEntities = Some(captionEntities))

  /** Disables automatic server-side content type detection for files uploaded using multipart/form-data
    */
  def withDisableContentTypeDetection(disable: Boolean): SendDocument =
    copy(disableContentTypeDetection = Some(disable))

  /** Sends the message [[https://telegram.org/blog/channels-2-0#silent-messages silently]]. Users will receive a
    * notification with no sound.
    */
  def withDisableNotification(disable: Boolean): SendDocument = copy(disableNotification = Some(disable))

  /** Protects the contents of the sent message from forwarding and saving
    */
  def withProtectContent(protect: Boolean): SendDocument = copy(protectContent = Some(protect))

  /** If the message is a reply, ID of the original message
    */
  def withReplyToMessageId(id: Int): SendDocument = copy(replyToMessageId = Some(id))

  /** Pass ''True'', if the message should be sent even if the specified replied-to message is not found
    */
  def withAllowSendingWithoutReply(allow: Boolean): SendDocument = copy(allowSendingWithoutReply = Some(allow))

  /** Additional interface options. An object for an
    * [[https://core.telegram.org/bots#inline-keyboards-and-on-the-fly-updating inline keyboard]],
    * [[https://core.telegram.org/bots#keyboards custom reply keyboard]], instructions to remove reply keyboard or to
    * force a reply from the user.
    */
  def withReplyMarkup(markup: Markup): SendDocument = copy(replyMarkup = Some(markup))
}
