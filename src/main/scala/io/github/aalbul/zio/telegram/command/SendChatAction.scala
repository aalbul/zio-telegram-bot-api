package io.github.aalbul.zio.telegram.command

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.command.SendChatAction.SendChatActionPayload
import io.github.aalbul.zio.telegram.domain.ChatActions.ChatAction
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object SendChatAction {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class SendChatActionPayload(chatId: String, action: ChatAction)

  def of(chatId: String, action: ChatAction): SendChatAction = SendChatAction(
    SendChatActionPayload(chatId = chatId, action = action)
  )
}

case class SendChatAction(payload: SendChatActionPayload) extends Command[Boolean] {
  override val name: String = "sendChatAction"

  override def parameters: ApiParameters = JsonBody(payload)
}
