package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.*

import scala.concurrent.duration.Duration

object SendVoice {

  /** Constructs minimal [[SendVoice]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param voice
    *   Audio file to send. Pass a file_id as String to send a file that exists on the Telegram servers (recommended),
    *   pass an HTTP URL as a String for Telegram to get a file from the Internet, or upload a new one using
    *   multipart/form-data. [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    * @return
    *   [[SendVoice]] builder
    */
  def of(chatId: String, voice: FileDescriptor): SendVoice = SendVoice(
    chatId = chatId,
    messageThreadId = None,
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

/** Use this method to send audio files, if you want Telegram clients to display the file as a playable voice message.
  * For this to work, your audio must be in an .OGG file encoded with OPUS (other formats may be sent as [[Audio]] or
  * [[Document]]). On success, the sent [[Message]] is returned. Bots can currently send voice messages of up to 50 MB
  * in size, this limit may be changed in the future.
  */
case class SendVoice(
  chatId: String,
  messageThreadId: Option[Long],
  voice: FileDescriptor,
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  duration: Option[Duration],
  disableNotification: Option[Boolean],
  protectContent: Option[Boolean],
  replyToMessageId: Option[Long],
  allowSendingWithoutReply: Option[Boolean],
  replyMarkup: Option[Markup]
) extends Command[Message] {
  override val name: String = "sendVoice"

  override def parameters: ApiParameters = MultipartBody.ofOpt(
    Some(stringPart("chat_id", chatId)),
    messageThreadId.map(stringPart("message_thread_id", _)),
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

  /** Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
    */
  def withMessageThreadId(messageThreadId: Long): SendVoice = copy(messageThreadId = Some(messageThreadId))

  /** Voice message caption, 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): SendVoice = copy(caption = Some(caption))

  /** Mode for parsing entities in the voice message caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): SendVoice = copy(parseMode = Some(parseMode))

  /** A JSON-serialized list of special entities that appear in the new caption, which can be specified instead of
    * ''parse_mode''
    */
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): SendVoice =
    copy(captionEntities = Some(captionEntities))

  /** Duration of the voice message in seconds
    */
  def withDuration(duration: Duration): SendVoice = copy(duration = Some(duration))

  /** Sends the message [[https://telegram.org/blog/channels-2-0#silent-messages silently]]. Users will receive a
    * notification with no sound.
    */
  def withDisableNotification(disable: Boolean): SendVoice = copy(disableNotification = Some(disable))

  /** Protects the contents of the sent message from forwarding and saving
    */
  def withProtectContent(protect: Boolean): SendVoice = copy(protectContent = Some(protect))

  /** If the message is a reply, ID of the original message
    */
  def withReplyToMessageId(id: Long): SendVoice = copy(replyToMessageId = Some(id))

  /** Pass ''True'', if the message should be sent even if the specified replied-to message is not found
    */
  def withAllowSendingWithoutReply(allow: Boolean): SendVoice = copy(allowSendingWithoutReply = Some(allow))

  /** Additional interface options. An object for an
    * [[https://core.telegram.org/bots#inline-keyboards-and-on-the-fly-updating inline keyboard]],
    * [[https://core.telegram.org/bots#keyboards custom reply keyboard]], instructions to remove reply keyboard or to
    * force a reply from the user.
    */
  def withReplyMarkup(markup: Markup): SendVoice = copy(replyMarkup = Some(markup))
}
