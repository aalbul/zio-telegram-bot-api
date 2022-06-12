package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.command.SendMessage.SendMessagePayload
import com.github.aalbul.zio.telegram.domain.ParseModes.ParseMode
import com.github.aalbul.zio.telegram.domain.{Markup, Message, MessageEntity}
import io.circe.generic.extras.ConfiguredJsonCodec

object SendMessage {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class SendMessagePayload(
    chatId: String,
    text: String,
    parseMode: Option[ParseMode],
    entities: Option[Seq[MessageEntity]],
    disableWebPagePreview: Option[Boolean],
    disableNotification: Option[Boolean],
    protectContent: Option[Boolean],
    replyToMessageId: Option[Long],
    allowSendingWithoutReply: Option[Boolean],
    replyMarkup: Option[Markup]
  )

  def of(chatId: String, text: String): SendMessage = SendMessage(
    SendMessagePayload(
      chatId = chatId,
      text = text,
      parseMode = None,
      entities = None,
      disableWebPagePreview = None,
      disableNotification = None,
      protectContent = None,
      replyToMessageId = None,
      allowSendingWithoutReply = None,
      replyMarkup = None
    )
  )
}

case class SendMessage(payload: SendMessagePayload) extends Command[Message] {
  override val name: String = "sendMessage"

  override def parameters: ApiParameters = JsonBody(payload)

  def withParseMode(parseMode: ParseMode): SendMessage = copy(payload.copy(parseMode = Some(parseMode)))
  def withEntities(entities: Seq[MessageEntity]): SendMessage = copy(payload.copy(entities = Some(entities)))
  def withDisableWebPagePreview(disable: Boolean): SendMessage = copy(
    payload.copy(disableWebPagePreview = Some(disable))
  )
  def withDisableNotification(disable: Boolean): SendMessage = copy(payload.copy(disableNotification = Some(disable)))
  def withProtectContent(protect: Boolean): SendMessage = copy(payload.copy(protectContent = Some(protect)))
  def withReplyToMessageId(id: Long): SendMessage = copy(payload.copy(replyToMessageId = Some(id)))
  def withAllowSendingWithoutReply(allow: Boolean): SendMessage = copy(
    payload.copy(allowSendingWithoutReply = Some(allow))
  )
  def withReplyMarkup(markup: Markup): SendMessage = copy(payload.copy(replyMarkup = Some(markup)))
}
