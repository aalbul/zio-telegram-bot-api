package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
case class WebAppData(data: String, buttonText: String)
