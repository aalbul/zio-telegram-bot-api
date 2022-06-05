package com.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
case class VideoNote(
  fileId: String,
  fileUniqueId: String,
  length: Int,
  duration: Int,
  thumb: Option[PhotoSize],
  fileSize: Option[Int]
)
