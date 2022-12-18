package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object Sticker {
  implicit val stickerJsonCodec: JsonValueCodec[Sticker] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

case class Sticker(
  fileId: String,
  fileUniqueId: String,
  `type`: StickerType,
  width: Int,
  height: Int,
  isAnimated: Boolean,
  isVideo: Boolean,
  thumb: Option[PhotoSize],
  emoji: Option[String],
  setName: Option[String],
  premiumAnimation: Option[File],
  maskPosition: Option[MaskPosition],
  customEmojiId: Option[String],
  fileSize: Option[Long]
)
