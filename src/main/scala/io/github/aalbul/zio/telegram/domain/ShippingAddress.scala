package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
case class ShippingAddress(
  countryCode: String,
  state: String,
  city: String,
  streetLine1: String,
  streetLine2: String,
  postCode: String
)
