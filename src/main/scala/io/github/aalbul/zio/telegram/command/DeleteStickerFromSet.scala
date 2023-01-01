package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.DeleteStickerFromSet.DeleteStickerFromSetPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object DeleteStickerFromSet {
  object DeleteStickerFromSetPayload {
    implicit val deleteStickerFromSetPayloadJsonCodec: JsonValueCodec[DeleteStickerFromSetPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class DeleteStickerFromSetPayload(sticker: String)

  /** Constructs minimal [[DeleteStickerFromSet]] command
    * @param sticker
    *   File identifier of the sticker
    * @return
    *   [[DeleteStickerFromSet]] builder
    */
  def of(sticker: String): DeleteStickerFromSet = DeleteStickerFromSet(
    DeleteStickerFromSetPayload(sticker = sticker)
  )
}

/** Use this method to delete a sticker from a set created by the bot. Returns True on success.
  */
case class DeleteStickerFromSet(payload: DeleteStickerFromSetPayload) extends Command[Boolean] {
  override val name: String = "deleteStickerFromSet"
  override def parameters: ApiParameters = JsonBody(payload)
}
