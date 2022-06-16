package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

@ConfiguredJsonCodec(decodeOnly = true)
case class PhotoSize(fileId: String, fileUniqueId: String, width: Int, height: Int, fileSize: Option[Int])
