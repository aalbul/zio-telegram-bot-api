package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.SetStickerPositionInSet.SetStickerPositionInSetPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object SetStickerPositionInSet {
  object SetStickerPositionInSetPayload {
    implicit val setGameScorePayloadJsonCodec: JsonValueCodec[SetStickerPositionInSetPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class SetStickerPositionInSetPayload(sticker: String, position: Long)

  /** Constructs minimal [[SetStickerPositionInSet]] command
    * @param sticker
    *   File identifier of the sticker
    * @param position
    *   New sticker position in the set, zero-based
    * @return
    *   [[SetStickerPositionInSet]] builder
    */
  def of(sticker: String, position: Long): SetStickerPositionInSet = SetStickerPositionInSet(
    SetStickerPositionInSetPayload(sticker = sticker, position = position)
  )
}

/** Use this method to move a sticker in a set created by the bot to a specific position. Returns True on success.
  */
case class SetStickerPositionInSet(payload: SetStickerPositionInSetPayload) extends Command[Boolean] {
  override val name: String = "setStickerPositionInSet"
  override def parameters: ApiParameters = JsonBody(payload)
}
