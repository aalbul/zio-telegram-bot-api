package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.{Markup, Message}

object SendSticker {

  /** Constructs minimal [[SendSticker]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param sticker
    *   Sticker to send. Pass a file_id as String to send a file that exists on the Telegram servers (recommended), pass
    *   an HTTP URL as a String for Telegram to get a .WEBP file from the Internet, or upload a new one using
    *   multipart/form-data. [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    * @return
    *   [[SendSticker]] builder
    */
  def of(chatId: String, sticker: FileDescriptor): SendSticker = SendSticker(
    chatId = chatId,
    messageThreadId = None,
    sticker = sticker,
    disableNotification = None,
    protectContent = None,
    replyToMessageId = None,
    allowSendingWithoutReply = None,
    replyMarkup = None
  )
}

/** Use this method to send static .WEBP, [[https://telegram.org/blog/animated-stickers animated]] .TGS, or
  * [[https://telegram.org/blog/video-stickers-better-reactions video]] .WEBM stickers. On success, the sent
  * [[https://core.telegram.org/bots/api#message Message]] is returned.
  */
case class SendSticker(
  chatId: String,
  messageThreadId: Option[Long],
  sticker: FileDescriptor,
  disableNotification: Option[Boolean],
  protectContent: Option[Boolean],
  replyToMessageId: Option[Long],
  allowSendingWithoutReply: Option[Boolean],
  replyMarkup: Option[Markup]
) extends Command[Message] {
  override val name: String = "sendSticker"
  override def parameters: ApiParameters = MultipartBody.ofOpt(
    Some(stringPart("chat_id", chatId)),
    messageThreadId.map(stringPart("message_thread_id", _)),
    Some(sticker.asMultipart("sticker")),
    disableNotification.map(stringPart("disable_notification", _)),
    protectContent.map(stringPart("protect_content", _)),
    replyToMessageId.map(stringPart("reply_to_message_id", _)),
    allowSendingWithoutReply.map(stringPart("allow_sending_without_reply", _)),
    replyMarkup.map(stringPart("reply_markup", _))
  )

  /** Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
    */
  def withMessageThreadId(messageThreadId: Long): SendSticker = copy(messageThreadId = Some(messageThreadId))

  /** Sends the message [[https://telegram.org/blog/channels-2-0#silent-messages silently]]. Users will receive a
    * notification with no sound.
    */
  def withDisableNotification(disableNotification: Boolean): SendSticker =
    copy(disableNotification = Some(disableNotification))

  /** Protects the contents of the sent message from forwarding and saving
    */
  def withProtectContent(protectContent: Boolean): SendSticker = copy(protectContent = Some(protectContent))

  /** If the message is a reply, ID of the original message
    */
  def withReplyToMessageId(replyToMessageId: Long): SendSticker = copy(replyToMessageId = Some(replyToMessageId))

  /** Pass True if the message should be sent even if the specified replied-to message is not found
    */
  def withAllowSendingWithoutReply(allowSendingWithoutReply: Boolean): SendSticker =
    copy(allowSendingWithoutReply = Some(allowSendingWithoutReply))

  /** Additional interface options. A JSON-serialized object for an
    * [[https://core.telegram.org/bots/features#inline-keyboards inline keyboard]],
    * [[https://core.telegram.org/bots/features#keyboards custom reply keyboard]], instructions to remove reply keyboard
    * or to force a reply from the user.
    */
  def withReplyMarkup(replyMarkup: Markup): SendSticker = copy(replyMarkup = Some(replyMarkup))
}
