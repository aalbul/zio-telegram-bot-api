package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.PinChatMessage.PinChatMessagePayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object PinChatMessage {
  object PinChatMessagePayload {
    implicit val pinChatMessagePayloadJsonCodec: JsonValueCodec[PinChatMessagePayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class PinChatMessagePayload(chatId: String, messageId: Long, disableNotification: Option[Boolean])

  /** Constructs minimal [[PinChatMessage]] command
    *
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param messageId
    *   Identifier of a message to pin
    * @return
    *   [[PinChatMessage]] builder
    */
  def of(chatId: String, messageId: Long): PinChatMessage = PinChatMessage(
    PinChatMessagePayload(chatId = chatId, messageId = messageId, disableNotification = None)
  )
}

/** Use this method to add a message to the list of pinned messages in a chat. If the chat is not a private chat, the
  * bot must be an administrator in the chat for this to work and must have the 'can_pin_messages' administrator right
  * in a supergroup or 'can_edit_messages' administrator right in a channel. Returns True on success.
  */
case class PinChatMessage(payload: PinChatMessagePayload) extends Command[Boolean] {
  override val name: String = "pinChatMessage"

  override def parameters: ApiParameters = JsonBody(payload)

  /** Pass True if it is not necessary to send a notification to all chat members about the new pinned message.
    * Notifications are always disabled in channels and private chats.
    */
  def withDisableNotification(disableNotification: Boolean): PinChatMessage = copy(
    payload.copy(disableNotification = Some(disableNotification))
  )
}
