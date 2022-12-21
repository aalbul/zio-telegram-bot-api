package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.UnpinAllChatMessages.UnpinAllChatMessagesPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.codecs.boolean
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object UnpinAllChatMessages {
  object UnpinAllChatMessagesPayload {
    implicit val unpinAllChatMessagesPayloadJsonCodec: JsonValueCodec[UnpinAllChatMessagesPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class UnpinAllChatMessagesPayload(chatId: String)

  /** Constructs minimal [[UnpinAllChatMessages]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @return
    *   [[UnpinAllChatMessages]] builder
    */
  def of(chatId: String): UnpinAllChatMessages = UnpinAllChatMessages(
    UnpinAllChatMessagesPayload(chatId = chatId)
  )
}

/** Use this method to clear the list of pinned messages in a chat. If the chat is not a private chat, the bot must be
  * an administrator in the chat for this to work and must have the 'can_pin_messages' administrator right in a
  * supergroup or 'can_edit_messages' administrator right in a channel. Returns True on success.
  */
case class UnpinAllChatMessages(payload: UnpinAllChatMessagesPayload) extends Command[Boolean] {
  override val name: String = "unpinAllChatMessages"

  override def parameters: ApiParameters = JsonBody(payload)
}
