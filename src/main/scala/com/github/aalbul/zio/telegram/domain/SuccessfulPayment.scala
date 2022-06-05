package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.OrderInfo.OrderInfoOps
import com.pengrad.telegrambot.model.SuccessfulPayment as LibSuccessfulPayment

object SuccessfulPayment {
  implicit class SuccessfulPaymentOps(successfulPayment: LibSuccessfulPayment) {
    def asScala: SuccessfulPayment = SuccessfulPayment(
      currency = successfulPayment.currency(),
      totalAmount = successfulPayment.totalAmount(),
      invoicePayload = successfulPayment.invoicePayload(),
      shippingOptionId = Option(successfulPayment.shippingOptionId()),
      orderInfo = Option(successfulPayment.orderInfo()).map(_.asScala),
      telegramPaymentChargeId = successfulPayment.telegramPaymentChargeId(),
      providerPaymentChargeId = successfulPayment.providerPaymentChargeId()
    )
  }
}

case class SuccessfulPayment(
  currency: String,
  totalAmount: Int,
  invoicePayload: String,
  shippingOptionId: Option[String],
  orderInfo: Option[OrderInfo],
  telegramPaymentChargeId: String,
  providerPaymentChargeId: String
)
