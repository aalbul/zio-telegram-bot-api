package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

@ConfiguredJsonCodec(decodeOnly = true)
case class Sticker(
  fileId: String,
  fileUniqueId: String,
  width: Int,
  height: Int,
  isAnimated: Boolean,
  isVideo: Boolean,
  thumb: Option[PhotoSize],
  emoji: Option[String],
  setName: Option[String],
  maskPosition: Option[MaskPosition]
)
