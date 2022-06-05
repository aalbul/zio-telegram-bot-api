package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.ShippingAddress.ShippingAddressOps
import com.github.aalbul.zio.telegram.domain.User.UserOps
import com.pengrad.telegrambot.model.ShippingQuery as LibShippingQuery

object ShippingQuery {
  implicit class ShippingQueryOps(query: LibShippingQuery) {
    def asScala: ShippingQuery = ShippingQuery(
      id = query.id(),
      from = query.from().asScala,
      invoicePayload = query.invoicePayload(),
      shippingAddress = query.shippingAddress().asScala
    )
  }
}

case class ShippingQuery(id: String, from: User, invoicePayload: String, shippingAddress: ShippingAddress)
