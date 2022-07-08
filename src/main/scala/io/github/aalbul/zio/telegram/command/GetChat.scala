package io.github.aalbul.zio.telegram.command

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.command.GetChat.GetChatPayload
import io.github.aalbul.zio.telegram.domain.Chat
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object GetChat {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class GetChatPayload(chatId: String)

  /** Constructs minimal [[GetChat]] command
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup or channel (in the format
    *   \@channelusername)
    * @return
    *   [[GetChat]] builder
    */
  def of(chatId: String): GetChat = GetChat(GetChatPayload(chatId))
}

/** Use this method to get up to date information about the chat (current name of the user for one-on-one conversations,
  * current username of a user, group or channel, etc.). Returns a [[Chat]] object on success.
  */
case class GetChat(payload: GetChatPayload) extends Command[Chat] {
  override val name: String = "getChat"

  override def parameters: ApiParameters = JsonBody(payload)
}
