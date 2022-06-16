package io.github.aalbul.zio.telegram.command

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.command.SendDice.SendDicePayload
import io.github.aalbul.zio.telegram.domain.DiceTypes.DiceType
import io.github.aalbul.zio.telegram.domain.{Markup, Message}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object SendDice {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class SendDicePayload(
    chatId: String,
    emoji: Option[DiceType],
    disableNotification: Option[Boolean],
    protectContent: Option[Boolean],
    replyToMessageId: Option[Long],
    allowSendingWithoutReply: Option[Boolean],
    replyMarkup: Option[Markup]
  )

  def of(chatId: String): SendDice = SendDice(
    SendDicePayload(
      chatId = chatId,
      emoji = None,
      disableNotification = None,
      protectContent = None,
      replyToMessageId = None,
      allowSendingWithoutReply = None,
      replyMarkup = None
    )
  )
}

case class SendDice(payload: SendDicePayload) extends Command[Message] {
  override val name: String = "sendDice"

  override def parameters: ApiParameters = JsonBody(payload)

  def withEmoji(emoji: DiceType): SendDice = copy(payload.copy(emoji = Some(emoji)))
  def withDisableNotification(disable: Boolean): SendDice = copy(payload.copy(disableNotification = Some(disable)))
  def withProtectContent(protect: Boolean): SendDice = copy(payload.copy(protectContent = Some(protect)))
  def withReplyToMessageId(id: Long): SendDice = copy(payload.copy(replyToMessageId = Some(id)))
  def withAllowSendingWithoutReply(allow: Boolean): SendDice = copy(
    payload.copy(allowSendingWithoutReply = Some(allow))
  )
  def withReplyMarkup(markup: Markup): SendDice = copy(payload.copy(replyMarkup = Some(markup)))
}
