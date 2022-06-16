package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

@ConfiguredJsonCodec(decodeOnly = true)
case class Voice(fileId: String, fileUniqueId: String, duration: Int, mimeType: Option[String], fileSize: Option[Int])
