package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.ShippingAddress.ShippingAddressOps
import com.pengrad.telegrambot.model.OrderInfo as LibOrderInfo

object OrderInfo {
  implicit class OrderInfoOps(orderInfo: LibOrderInfo) {
    def asScala: OrderInfo = OrderInfo(
      name = Option(orderInfo.name()),
      phoneNumber = Option(orderInfo.phoneNumber()),
      email = Option(orderInfo.email()),
      shippingAddress = Option(orderInfo.shippingAddress()).map(_.asScala)
    )
  }
}

case class OrderInfo(
  name: Option[String],
  phoneNumber: Option[String],
  email: Option[String],
  shippingAddress: Option[ShippingAddress]
)
