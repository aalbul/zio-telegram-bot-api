package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
case class Audio(
  fileId: String,
  fileUniqueId: String,
  duration: Int,
  performer: Option[String],
  title: Option[String],
  fileName: Option[String],
  mimeType: Option[String],
  fileSize: Option[Int],
  thumb: Option[PhotoSize]
)
