package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}

object StickerSet {
  implicit val stickerSetJsonCodec: JsonValueCodec[StickerSet] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[StickerSet]]
    * @param name
    *   Sticker set name
    * @param title
    *   Sticker set title
    * @param stickerType
    *   Type of stickers in the set, currently one of “regular”, “mask”, “custom_emoji”
    * @param isAnimated
    *   True, if the sticker set contains [[https://telegram.org/blog/animated-stickers animated stickers]]
    * @param isVideo
    *   True, if the sticker set contains [[https://telegram.org/blog/video-stickers-better-reactions video stickers]]
    * @param stickers
    *   List of all set stickers
    * @return
    *   [[StickerSet]] builder
    */
  def of(
    name: String,
    title: String,
    stickerType: StickerType,
    isAnimated: Boolean,
    isVideo: Boolean,
    stickers: Seq[Sticker]
  ): StickerSet = StickerSet(
    name = name,
    title = title,
    stickerType = stickerType,
    isAnimated = isAnimated,
    isVideo = isVideo,
    stickers = stickers,
    thumb = None
  )
}

/** This object represents a sticker set.
  */
case class StickerSet(
  name: String,
  title: String,
  stickerType: StickerType,
  isAnimated: Boolean,
  isVideo: Boolean,
  stickers: Seq[Sticker],
  thumb: Option[PhotoSize]
) {

  /** Sticker set thumbnail in the .WEBP, .TGS, or .WEBM format
    */
  def withThumb(thumb: PhotoSize): StickerSet = copy(thumb = Some(thumb))
}
