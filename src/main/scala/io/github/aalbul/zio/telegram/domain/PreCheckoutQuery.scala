package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
case class PreCheckoutQuery(
  id: String,
  from: User,
  currency: String,
  totalAmount: Int,
  invoicePayload: String,
  shippingOptionId: Option[String],
  orderInfo: Option[OrderInfo]
)
