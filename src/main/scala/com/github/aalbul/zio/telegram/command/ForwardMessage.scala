package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.command.ForwardMessage.ForwardMessageRequest
import com.github.aalbul.zio.telegram.domain.Message
import io.circe.generic.extras.ConfiguredJsonCodec

object ForwardMessage {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class ForwardMessageRequest(
    chatId: String,
    fromChatId: String,
    messageId: Long,
    disableNotification: Option[Boolean],
    protectContent: Option[Boolean]
  )

  def of(messageId: Long, fromChatId: String, toChatId: String): ForwardMessage = ForwardMessage(
    ForwardMessageRequest(
      chatId = toChatId,
      fromChatId = fromChatId,
      messageId = messageId,
      disableNotification = None,
      protectContent = None
    )
  )
}

case class ForwardMessage(request: ForwardMessageRequest) extends Command[Message] {
  override val name: String = "forwardMessage"
  override def parameters: ApiParameters = JsonBody(request)

  def withDisableNotification(disable: Boolean): ForwardMessage = copy(
    request.copy(disableNotification = Some(disable))
  )
  def withProtectContent(protect: Boolean): ForwardMessage = copy(request.copy(protectContent = Some(protect)))
}
