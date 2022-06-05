package com.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

import java.time.Instant

@ConfiguredJsonCodec(decodeOnly = true)
case class PassportFile(fileId: String, fileUniqueId: String, fileSize: Int, fileDate: Instant)
