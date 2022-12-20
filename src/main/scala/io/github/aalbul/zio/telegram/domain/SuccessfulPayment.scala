package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object SuccessfulPayment {
  implicit val successfulPaymentJsonCodec: JsonValueCodec[SuccessfulPayment] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[SuccessfulPayment]]
    * @param currency
    *   Three-letter ISO 4217 [[https://core.telegram.org/bots/payments#supported-currencies currency]] code
    * @param totalAmount
    *   Total price in the smallest units of the currency (integer, not float/double). For example, for a price of US$
    * 1.45 pass amount = 145. See the exp parameter in
    * [[https://core.telegram.org/bots/payments/currencies.json currencies.json]], it shows the number of digits past
    * the decimal point for each currency (2 for the majority of currencies).
    * @param invoicePayload
    *   Bot specified invoice payload
    * @param telegramPaymentChargeId
    *   Telegram payment identifier
    * @param providerPaymentChargeId
    *   Provider payment identifier
    * @return
    *   [[SuccessfulPayment]] builder
    */
  def of(
    currency: String,
    totalAmount: Long,
    invoicePayload: String,
    telegramPaymentChargeId: String,
    providerPaymentChargeId: String
  ): SuccessfulPayment = SuccessfulPayment(
    currency = currency,
    totalAmount = totalAmount,
    invoicePayload = invoicePayload,
    shippingOptionId = None,
    orderInfo = None,
    telegramPaymentChargeId = telegramPaymentChargeId,
    providerPaymentChargeId = providerPaymentChargeId
  )
}

/** This object contains basic information about a successful payment. */
case class SuccessfulPayment(
  currency: String,
  totalAmount: Long,
  invoicePayload: String,
  shippingOptionId: Option[String],
  orderInfo: Option[OrderInfo],
  telegramPaymentChargeId: String,
  providerPaymentChargeId: String
) {

  /** Identifier of the shipping option chosen by the user */
  def withShippingOptionId(optionId: String): SuccessfulPayment = copy(shippingOptionId = Some(optionId))

  /** Order information provided by the user */
  def withOrderInfo(orderInfo: OrderInfo): SuccessfulPayment = copy(orderInfo = Some(orderInfo))
}
