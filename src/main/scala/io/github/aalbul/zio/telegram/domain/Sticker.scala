package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object Sticker {
  implicit val stickerJsonCodec: JsonValueCodec[Sticker] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  implicit val stickerSeqJsonCodec: JsonValueCodec[Seq[Sticker]] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[Sticker]]
    * @param fileId
    *   Identifier for this file, which can be used to download or reuse the file
    * @param fileUniqueId
    *   Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be
    *   used to download or reuse the file.
    * @param `type`
    *   Type of the sticker, currently one of “regular”, “mask”, “custom_emoji”. The type of the sticker is independent
    *   from its format, which is determined by the fields is_animated and is_video.
    * @param width
    *   Sticker width
    * @param height
    *   Sticker height
    * @param isAnimated
    *   True, if the sticker is [[https://telegram.org/blog/animated-stickers animated]]
    * @param isVideo
    *   True, if the sticker is a [[https://telegram.org/blog/video-stickers-better-reactions video sticker]]
    * @return
    *   [[Sticker]] builder
    */
  def of(
    fileId: String,
    fileUniqueId: String,
    `type`: StickerType,
    width: Int,
    height: Int,
    isAnimated: Boolean,
    isVideo: Boolean
  ): Sticker = Sticker(
    fileId = fileId,
    fileUniqueId = fileUniqueId,
    `type` = `type`,
    width = width,
    height = height,
    isAnimated = isAnimated,
    isVideo = isVideo,
    thumb = None,
    emoji = None,
    setName = None,
    premiumAnimation = None,
    maskPosition = None,
    customEmojiId = None,
    fileSize = None
  )
}

/** This object represents a sticker.
  */
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
) {

  /** Sticker thumbnail in the .WEBP or .JPG format
    */
  def withThumb(thumb: PhotoSize): Sticker = copy(thumb = Some(thumb))

  /** Emoji associated with the sticker
    */
  def withEmoji(emoji: String): Sticker = copy(emoji = Some(emoji))

  /** Name of the sticker set to which the sticker belongs
    */
  def withSetName(setName: String): Sticker = copy(setName = Some(setName))

  /** For premium regular stickers, premium animation for the sticker
    */
  def withPremiumAnimation(premiumAnimation: File): Sticker = copy(premiumAnimation = Some(premiumAnimation))

  /** For mask stickers, the position where the mask should be placed
    */
  def withMaskPosition(maskPosition: MaskPosition): Sticker = copy(maskPosition = Some(maskPosition))

  /** For custom emoji stickers, unique identifier of the custom emoji
    */
  def withCustomEmojiId(customEmojiId: String): Sticker = copy(customEmojiId = Some(customEmojiId))

  /** File size in bytes
    */
  def withFileSize(fileSize: Long): Sticker = copy(fileSize = Some(fileSize))
}
