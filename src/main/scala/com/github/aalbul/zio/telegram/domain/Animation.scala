package com.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
case class Animation(
  fileId: String,
  fileUniqueId: String,
  width: Int,
  height: Int,
  duration: Int,
  thumb: Option[PhotoSize],
  fileName: Option[String],
  mimeType: Option[String],
  fileSize: Option[Int]
)
