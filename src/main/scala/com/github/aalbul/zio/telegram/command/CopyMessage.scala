package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.command.CopyMessage.CopyMessageRequest
import com.github.aalbul.zio.telegram.domain.ParseModes.ParseMode
import com.github.aalbul.zio.telegram.domain.{Markup, MessageEntity, MessageId}
import io.circe.generic.extras.ConfiguredJsonCodec

object CopyMessage {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class CopyMessageRequest(
    chatId: String,
    fromChatId: String,
    messageId: String,
    caption: Option[String],
    parseMode: Option[ParseMode],
    captionEntities: Option[Seq[MessageEntity]],
    disableNotification: Option[Boolean],
    protectContent: Option[Boolean],
    replyToMessageId: Option[Long],
    allowSendingWithoutReply: Option[Boolean],
    replyMarkup: Option[Markup]
  )

  def of(chatId: String, fromChatId: String, messageId: String): CopyMessage = CopyMessage(
    CopyMessageRequest(
      chatId = chatId,
      fromChatId = fromChatId,
      messageId = messageId,
      caption = None,
      parseMode = None,
      captionEntities = None,
      disableNotification = None,
      protectContent = None,
      replyToMessageId = None,
      allowSendingWithoutReply = None,
      replyMarkup = None
    )
  )
}

case class CopyMessage(request: CopyMessageRequest) extends Command[MessageId] {
  override val name: String = "copyMessage"
  override def parameters: ApiParameters = JsonBody(request)

  def withCaption(caption: String): CopyMessage = copy(request.copy(caption = Some(caption)))
  def withParseMode(parseMode: ParseMode): CopyMessage = copy(request.copy(parseMode = Some(parseMode)))
  def withCaptionEntities(entities: Seq[MessageEntity]): CopyMessage = copy(
    request.copy(captionEntities = Some(entities))
  )
  def withDisableNotification(disable: Boolean): CopyMessage = copy(request.copy(disableNotification = Some(disable)))
  def withProtectContent(protect: Boolean): CopyMessage = copy(request.copy(protectContent = Some(protect)))
  def withReplyToMessageId(id: Long): CopyMessage = copy(request.copy(replyToMessageId = Some(id)))
  def withAllowSendingWithoutReply(allow: Boolean): CopyMessage = copy(
    request.copy(allowSendingWithoutReply = Some(allow))
  )
  def withReplyMarkup(markup: Markup): CopyMessage = copy(request.copy(replyMarkup = Some(markup)))
}
