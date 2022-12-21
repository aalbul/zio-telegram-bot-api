package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.SetChatStickerSet.SetChatStickerSetPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object SetChatStickerSet {
  object SetChatStickerSetPayload {
    implicit val setChatStickerSetPayloadJsonCodec: JsonValueCodec[SetChatStickerSetPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class SetChatStickerSetPayload(chatId: String, stickerSetName: String)

  /** Constructs minimal [[SetChatStickerSet]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target supergroup (in the format @supergroupusername)
    * @param stickerSetName
    *   Name of the sticker set to be set as the group sticker set
    * @return
    *   [[SetChatStickerSet]] builder
    */
  def of(chatId: String, stickerSetName: String): SetChatStickerSet = SetChatStickerSet(
    SetChatStickerSetPayload(chatId = chatId, stickerSetName = stickerSetName)
  )
}

/** Use this method to set a new group sticker set for a supergroup. The bot must be an administrator in the chat for
  * this to work and must have the appropriate administrator rights. Use the field can_set_sticker_set optionally
  * returned in [[https://core.telegram.org/bots/api#getchat getChat]] requests to check if the bot can use this method.
  * Returns True on success.
  */
case class SetChatStickerSet(payload: SetChatStickerSetPayload) extends Command[Boolean] {
  override val name: String = "setChatStickerSet"
  override def parameters: ApiParameters = JsonBody(payload)
}
