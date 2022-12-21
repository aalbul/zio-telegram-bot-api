package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.UnpinChatMessage.UnpinChatMessagePayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.codecs.boolean

object UnpinChatMessage {
  object UnpinChatMessagePayload {
    implicit val unpinChatMessagePayloadJsonCodec: JsonValueCodec[UnpinChatMessagePayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class UnpinChatMessagePayload(chatId: String, messageId: Option[Long])

  /** Use this method to remove a message from the list of pinned messages in a chat. If the chat is not a private chat,
    * the bot must be an administrator in the chat for this to work and must have the 'can_pin_messages' administrator
    * right in a supergroup or 'can_edit_messages' administrator right in a channel. Returns True on success.
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @return
    *   [[UnpinChatMessage]] builder
    */
  def of(chatId: String): UnpinChatMessage = UnpinChatMessage(
    UnpinChatMessagePayload(chatId = chatId, messageId = None)
  )
}

case class UnpinChatMessage(payload: UnpinChatMessagePayload) extends Command[Boolean] {
  override val name: String = "unpinChatMessage"

  override def parameters: ApiParameters = JsonBody(payload)

  /** Identifier of a message to unpin. If not specified, the most recent pinned message (by sending date) will be
    * unpinned.
    */
  def withMessageId(messageId: Long): UnpinChatMessage = copy(payload.copy(messageId = Some(messageId)))
}
