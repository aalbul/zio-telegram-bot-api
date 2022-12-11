package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.StickerTypes.StickerType

@ConfiguredJsonCodec(decodeOnly = true)
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
