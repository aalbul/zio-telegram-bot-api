package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.DeleteMessage.DeleteMessagePayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object DeleteMessage {
  object DeleteMessagePayload {
    implicit val deleteMessagePayloadJsonCodec: JsonValueCodec[DeleteMessagePayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class DeleteMessagePayload(chatId: String, messageId: Long)

  /** Constructs minimal [[DeleteMessage]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param messageId
    *   Identifier of the message to delete
    * @return
    *   [[DeleteMessage]] builder
    */
  def of(chatId: String, messageId: Long): DeleteMessage = DeleteMessage(
    DeleteMessagePayload(chatId = chatId, messageId = messageId)
  )
}

/** Use this method to delete a message, including service messages, with the following limitations:
  *   - A message can only be deleted if it was sent less than 48 hours ago.
  *   - Service messages about a supergroup, channel, or forum topic creation can't be deleted.
  *   - A dice message in a private chat can only be deleted if it was sent more than 24 hours ago.
  *   - Bots can delete outgoing messages in private chats, groups, and supergroups.
  *   - Bots can delete incoming messages in private chats.
  *   - Bots granted can_post_messages permissions can delete outgoing messages in channels.
  *   - If the bot is an administrator of a group, it can delete any message there.
  *   - If the bot has can_delete_messages permission in a supergroup or a channel, it can delete any message there.
  *     Returns True on success.
  */
case class DeleteMessage(payload: DeleteMessagePayload) extends Command[Boolean] {
  override val name: String = "deleteMessage"
  override def parameters: ApiParameters = JsonBody(payload)
}
