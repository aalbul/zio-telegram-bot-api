package io.github.aalbul.zio.telegram.command

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.command.UnbanChatSenderChat.UnbanChatSenderChatPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object UnbanChatSenderChat {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class UnbanChatSenderChatPayload(chatId: String, senderChatId: Long)

  /** Constructs minimal [[UnbanChatSenderChat]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param senderChatId
    *   Unique identifier of the target sender chat
    * @return
    *   [[UnbanChatSenderChat]] builder
    */
  def of(chatId: String, senderChatId: Long): UnbanChatSenderChat = UnbanChatSenderChat(
    UnbanChatSenderChatPayload(chatId, senderChatId)
  )
}

/** Use this method to unban a previously banned channel chat in a supergroup or channel. The bot must be an
  * administrator for this to work and must have the appropriate administrator rights. Returns ''True'' on success.
  */
case class UnbanChatSenderChat(payload: UnbanChatSenderChatPayload) extends Command[Boolean] {
  override val name: String = "unbanChatSenderChat"

  override def parameters: ApiParameters = JsonBody(payload)
}
