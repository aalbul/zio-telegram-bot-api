package com.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
case class Invoice(title: String, description: String, startParameter: String, currency: String, totalAmount: Int)
