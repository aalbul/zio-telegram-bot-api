package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.DeleteChatStickerSet.DeleteChatStickerSetPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object DeleteChatStickerSet {
  object DeleteChatStickerSetPayload {
    implicit val deleteChatStickerSetPayloadJsonCodec: JsonValueCodec[DeleteChatStickerSetPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class DeleteChatStickerSetPayload(chatId: String)

  /** Constructs minimal [[DeleteChatStickerSet]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @return
    *   [[DeleteChatStickerSet]] builder
    */
  def of(chatId: String): DeleteChatStickerSet = DeleteChatStickerSet(DeleteChatStickerSetPayload(chatId = chatId))
}

/** Use this method to delete a group sticker set from a supergroup. The bot must be an administrator in the chat for
  * this to work and must have the appropriate administrator rights. Use the field can_set_sticker_set optionally
  * returned in [[https://core.telegram.org/bots/api#getchat getChat]] requests to check if the bot can use this method.
  * Returns True on success.
  */
case class DeleteChatStickerSet(payload: DeleteChatStickerSetPayload) extends Command[Boolean] {
  override val name: String = "deleteChatStickerSet"
  override def parameters: ApiParameters = JsonBody(payload)
}
