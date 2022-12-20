package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.{InputMedia, Message}

object SendMediaGroup {

  /** Constructs minimal [[SendMediaGroup]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param media
    *   A JSON-serialized array describing messages to be sent, must include 2-10 items
    * @return
    *   [[SendMediaGroup]] builder
    */
  def of(chatId: String, media: Seq[InputMedia]): SendMediaGroup = SendMediaGroup(
    chatId = chatId,
    messageThreadId = None,
    media = media,
    disableNotification = None,
    protectContent = None,
    replyToMessageId = None,
    allowSendingWithoutReply = None
  )
}

/** Use this method to send a group of photos, videos, documents or audios as an album. Documents and audio files can be
  * only grouped in an album with messages of the same type. On success, an array of [[Message]] that were sent is
  * returned.
  */
case class SendMediaGroup(
  chatId: String,
  messageThreadId: Option[Long],
  media: Seq[InputMedia],
  disableNotification: Option[Boolean],
  protectContent: Option[Boolean],
  replyToMessageId: Option[Long],
  allowSendingWithoutReply: Option[Boolean]
) extends Command[Seq[Message]] {

  override val name: String = "sendMediaGroup"

  private def attachedFiles: Seq[FilePart] =
    media
      .flatMap(media => Seq(Some(media.media), media.thumb).flatten)
      .collect { case PathBasedFileDescriptor(path) => FilePart(path.toAbsolutePath.toString, path) }

  override def parameters: ApiParameters =
    MultipartBody
      .ofOpt(
        Some(stringPart("chat_id", chatId)),
        messageThreadId.map(stringPart("message_thread_id", _)),
        Some(stringPart("media", JsonBody(media).toJson)),
        disableNotification.map(stringPart("disable_notification", _)),
        protectContent.map(stringPart("protect_content", _)),
        replyToMessageId.map(stringPart("reply_to_message_id", _)),
        allowSendingWithoutReply.map(stringPart("allow_sending_without_reply", _)),
      )
      .plus(attachedFiles)

  /** Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
    */
  def withMessageThreadId(messageThreadId: Long): SendMediaGroup = copy(messageThreadId = Some(messageThreadId))

  /** Sends the message [[https://telegram.org/blog/channels-2-0#silent-messages silently]]. Users will receive a
    * notification with no sound.
    */
  def withDisableNotification(disable: Boolean): SendMediaGroup = copy(disableNotification = Some(disable))

  /** Protects the contents of the sent message from forwarding and saving
    */
  def withProtectContent(protect: Boolean): SendMediaGroup = copy(protectContent = Some(protect))

  /** If the message is a reply, ID of the original message
    */
  def withReplyToMessageId(id: Long): SendMediaGroup = copy(replyToMessageId = Some(id))

  /** Pass ''True'', if the message should be sent even if the specified replied-to message is not found
    */
  def withAllowSendingWithoutReply(allow: Boolean): SendMediaGroup = copy(allowSendingWithoutReply = Some(allow))
}
