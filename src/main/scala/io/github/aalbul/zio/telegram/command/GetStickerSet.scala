package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.GetStickerSet.GetStickerSetPayload
import io.github.aalbul.zio.telegram.domain.StickerSet

object GetStickerSet {
  object GetStickerSetPayload {
    implicit val getStickerSetPayloadJsonCodec: JsonValueCodec[GetStickerSetPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class GetStickerSetPayload(name: String)

  /** Constructs minimal [[GetStickerSet]] command
    * @param name
    *   Name of the sticker set
    * @return
    *   [[GetStickerSet]] builder
    */
  def of(name: String): GetStickerSet = GetStickerSet(GetStickerSetPayload(name = name))
}

/** Use this method to get a sticker set. On success, a [[https://core.telegram.org/bots/api#stickerset StickerSet]]
  * object is returned.
  */
case class GetStickerSet(payload: GetStickerSetPayload) extends Command[StickerSet] {
  override val name: String = "getStickerSet"
  override def parameters: ApiParameters = JsonBody(payload)
}
