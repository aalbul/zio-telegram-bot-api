package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.{Markup, Message, MessageEntity, ParseMode}

object SendPhoto {

  /** Constructs minimal [[SendPhoto]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param photo
    *   Photo to send. Pass a file_id as String to send a photo that exists on the Telegram servers (recommended), pass
    *   an HTTP URL as a String for Telegram to get a photo from the Internet, or upload a new photo using
    *   multipart/form-data. The photo must be at most 10 MB in size. The photo's width and height must not exceed 10000
    *   in total. Width and height ratio must be at most 20.
    *   [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    * @return
    *   [[SendPhoto]] builder
    */
  def of(chatId: String, photo: FileDescriptor): SendPhoto = new SendPhoto(
    chatId = chatId,
    photo = photo,
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

/** Use this method to send photos. On success, the sent [[Message]] is returned.
  */
case class SendPhoto(
  chatId: String,
  photo: FileDescriptor,
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  disableNotification: Option[Boolean],
  protectContent: Option[Boolean],
  replyToMessageId: Option[Long],
  allowSendingWithoutReply: Option[Boolean],
  replyMarkup: Option[Markup]
) extends Command[Message] {
  override val name: String = "sendPhoto"

  override def parameters: ApiParameters = MultipartBody.ofOpt(
    Some(stringPart("chat_id", chatId)),
    Some(photo.asMultipart("photo")),
    caption.map(stringPart("caption", _)),
    parseMode.map(mode => stringPart("parse_mode", mode.toString)),
    captionEntities.map(entities => stringPart("caption_entities", JsonBody(entities).toJson)),
    disableNotification.map(stringPart("disable_notification", _)),
    protectContent.map(stringPart("protect_content", _)),
    replyToMessageId.map(stringPart("reply_to_message_id", _)),
    allowSendingWithoutReply.map(stringPart("allow_sending_without_reply", _)),
    replyMarkup.map(markup => stringPart("reply_markup", JsonBody(markup).toJson))
  )

  /** New caption for media, 0-1024 characters after entities parsing. If not specified, the original caption is kept
    */
  def withCaption(caption: String): SendPhoto = copy(caption = Some(caption))

  /** Mode for parsing entities in the new caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): SendPhoto = copy(parseMode = Some(parseMode))

  /** A JSON-serialized list of special entities that appear in the new caption, which can be specified instead of
    * ''parse_mode''
    */
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): SendPhoto =
    copy(captionEntities = Some(captionEntities))

  /** Sends the message [[https://telegram.org/blog/channels-2-0#silent-messages silently]]. Users will receive a
    * notification with no sound.
    */
  def withDisableNotification(disable: Boolean): SendPhoto = copy(disableNotification = Some(disable))

  /** Protects the contents of the sent message from forwarding and saving
    */
  def withProtectContent(protect: Boolean): SendPhoto = copy(protectContent = Some(protect))

  /** If the message is a reply, ID of the original message
    */
  def withReplyToMessageId(id: Long): SendPhoto = copy(replyToMessageId = Some(id))

  /** Pass ''True'', if the message should be sent even if the specified replied-to message is not found
    */
  def withAllowSendingWithoutReply(allow: Boolean): SendPhoto = copy(allowSendingWithoutReply = Some(allow))

  /** Additional interface options. An object for an
    * [[https://core.telegram.org/bots#inline-keyboards-and-on-the-fly-updating inline keyboard]],
    * [[https://core.telegram.org/bots#keyboards custom reply keyboard]], instructions to remove reply keyboard or to
    * force a reply from the user.
    */
  def withReplyMarkup(markup: Markup): SendPhoto = copy(replyMarkup = Some(markup))
}
