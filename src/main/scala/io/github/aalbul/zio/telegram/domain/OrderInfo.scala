package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
case class OrderInfo(
  name: Option[String],
  phoneNumber: Option[String],
  email: Option[String],
  shippingAddress: Option[ShippingAddress]
)
