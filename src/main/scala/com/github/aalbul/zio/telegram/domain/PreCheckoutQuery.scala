package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.OrderInfo.OrderInfoOps
import com.github.aalbul.zio.telegram.domain.User.UserOps
import com.pengrad.telegrambot.model.PreCheckoutQuery as LibPreCheckoutQuery

object PreCheckoutQuery {
  implicit class PreCheckoutQueryOps(query: LibPreCheckoutQuery) {
    def asScala: PreCheckoutQuery = PreCheckoutQuery(
      id = query.id(),
      from = query.from().asScala,
      currency = query.currency(),
      totalAmount = query.totalAmount(),
      invoicePayload = query.invoicePayload(),
      shippingOptionId = Option(query.shippingOptionId()),
      orderInfo = Option(query.orderInfo()).map(_.asScala)
    )
  }
}

case class PreCheckoutQuery(
  id: String,
  from: User,
  currency: String,
  totalAmount: Int,
  invoicePayload: String,
  shippingOptionId: Option[String],
  orderInfo: Option[OrderInfo]
)
