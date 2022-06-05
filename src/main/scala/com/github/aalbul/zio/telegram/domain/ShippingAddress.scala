package com.github.aalbul.zio.telegram.domain

import com.pengrad.telegrambot.model.ShippingAddress as LibShippingAddress

object ShippingAddress {
  implicit class ShippingAddressOps(shippingAddress: LibShippingAddress) {
    def asScala: ShippingAddress = ShippingAddress(
      countryCode = shippingAddress.countryCode(),
      state = shippingAddress.state(),
      city = shippingAddress.city(),
      streetLine1 = shippingAddress.streetLine1(),
      streetLine2 = shippingAddress.streetLine2(),
      postCode = shippingAddress.postCode()
    )
  }
}

case class ShippingAddress(
  countryCode: String,
  state: String,
  city: String,
  streetLine1: String,
  streetLine2: String,
  postCode: String
)
