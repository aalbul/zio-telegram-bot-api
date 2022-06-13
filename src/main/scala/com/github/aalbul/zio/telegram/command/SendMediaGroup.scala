package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import com.github.aalbul.zio.telegram.domain.{InputMedia, Message}

object SendMediaGroup {
  def of(chatId: String, media: Seq[InputMedia]): SendMediaGroup = SendMediaGroup(
    chatId = chatId,
    media = media,
    disableNotification = None,
    protectContent = None,
    replyToMessageId = None,
    allowSendingWithoutReply = None
  )
}

case class SendMediaGroup(
  chatId: String,
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
        Some(stringPart("media", JsonBody(media).toJson)),
        disableNotification.map(stringPart("disable_notification", _)),
        protectContent.map(stringPart("protect_content", _)),
        replyToMessageId.map(stringPart("reply_to_message_id", _)),
        allowSendingWithoutReply.map(stringPart("allow_sending_without_reply", _)),
      )
      .plus(attachedFiles)

  def withDisableNotification(disable: Boolean): SendMediaGroup = copy(disableNotification = Some(disable))
  def withProtectContent(protect: Boolean): SendMediaGroup = copy(protectContent = Some(protect))
  def withReplyToMessageId(id: Long): SendMediaGroup = copy(replyToMessageId = Some(id))
  def withAllowSendingWithoutReply(allow: Boolean): SendMediaGroup = copy(allowSendingWithoutReply = Some(allow))
}
