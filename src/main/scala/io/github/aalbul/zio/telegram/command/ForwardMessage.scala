package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.ForwardMessage.ForwardMessagePayload
import io.github.aalbul.zio.telegram.domain.Message
import io.circe.generic.extras.ConfiguredJsonCodec

object ForwardMessage {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class ForwardMessagePayload(
    chatId: String,
    fromChatId: String,
    messageId: Long,
    disableNotification: Option[Boolean],
    protectContent: Option[Boolean]
  )

  def of(messageId: Long, fromChatId: String, toChatId: String): ForwardMessage = ForwardMessage(
    ForwardMessagePayload(
      chatId = toChatId,
      fromChatId = fromChatId,
      messageId = messageId,
      disableNotification = None,
      protectContent = None
    )
  )
}

case class ForwardMessage(payload: ForwardMessagePayload) extends Command[Message] {
  override val name: String = "forwardMessage"
  override def parameters: ApiParameters = JsonBody(payload)

  def withDisableNotification(disable: Boolean): ForwardMessage = copy(
    payload.copy(disableNotification = Some(disable))
  )
  def withProtectContent(protect: Boolean): ForwardMessage = copy(payload.copy(protectContent = Some(protect)))
}
