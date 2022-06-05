package com.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
case class MaskPosition(point: String, xShift: Float, yShift: Float, scale: Float)
