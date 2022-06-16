package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
case class ShippingQuery(id: String, from: User, invoicePayload: String, shippingAddress: ShippingAddress)
