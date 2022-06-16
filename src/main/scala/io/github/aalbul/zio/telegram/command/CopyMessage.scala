package io.github.aalbul.zio.telegram.command

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.command.CopyMessage.CopyMessagePayload
import io.github.aalbul.zio.telegram.domain.ParseModes.ParseMode
import io.github.aalbul.zio.telegram.domain.{Markup, MessageEntity, MessageId}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object CopyMessage {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class CopyMessagePayload(
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
    CopyMessagePayload(
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

case class CopyMessage(payload: CopyMessagePayload) extends Command[MessageId] {
  override val name: String = "copyMessage"
  override def parameters: ApiParameters = JsonBody(payload)

  def withCaption(caption: String): CopyMessage = copy(payload.copy(caption = Some(caption)))
  def withParseMode(parseMode: ParseMode): CopyMessage = copy(payload.copy(parseMode = Some(parseMode)))
  def withCaptionEntities(entities: Seq[MessageEntity]): CopyMessage = copy(
    payload.copy(captionEntities = Some(entities))
  )
  def withDisableNotification(disable: Boolean): CopyMessage = copy(payload.copy(disableNotification = Some(disable)))
  def withProtectContent(protect: Boolean): CopyMessage = copy(payload.copy(protectContent = Some(protect)))
  def withReplyToMessageId(id: Long): CopyMessage = copy(payload.copy(replyToMessageId = Some(id)))
  def withAllowSendingWithoutReply(allow: Boolean): CopyMessage = copy(
    payload.copy(allowSendingWithoutReply = Some(allow))
  )
  def withReplyMarkup(markup: Markup): CopyMessage = copy(payload.copy(replyMarkup = Some(markup)))
}
