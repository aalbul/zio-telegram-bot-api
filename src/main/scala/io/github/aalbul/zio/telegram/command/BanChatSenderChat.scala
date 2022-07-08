package io.github.aalbul.zio.telegram.command

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.command.BanChatSenderChat.BanChatSenderChatPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object BanChatSenderChat {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class BanChatSenderChatPayload(chatId: String, senderChatId: Long)

  /** Constructs minimal [[BanChatSenderChat]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param senderChatId
    *   Unique identifier of the target sender chat
    * @return
    *   [[BanChatSenderChat]] builder
    */
  def of(chatId: String, senderChatId: Long): BanChatSenderChat = BanChatSenderChat(
    BanChatSenderChatPayload(chatId, senderChatId)
  )
}

/** Use this method to ban a channel chat in a supergroup or a channel. Until the chat is
  * [[https://core.telegram.org/bots/api#unbanchatsenderchat unbanned]], the owner of the banned chat won't be able to
  * send messages on behalf of any of their channels. The bot must be an administrator in the supergroup or channel for
  * this to work and must have the appropriate administrator rights. Returns ''True'' on success.
  */
case class BanChatSenderChat(payload: BanChatSenderChatPayload) extends Command[Boolean] {
  override val name: String = "banChatSenderChat"

  override def parameters: ApiParameters = JsonBody(payload)
}
