package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object PreCheckoutQuery {
  implicit val preCheckoutQueryJsonCodec: JsonValueCodec[PreCheckoutQuery] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[PreCheckoutQuery]]
    * @param id
    *   Unique query identifier
    * @param from
    *   User who sent the query
    * @param currency
    *   Three-letter ISO 4217 [[https://core.telegram.org/bots/payments#supported-currencies currency]] code
    * @param totalAmount
    *   Total price in the smallest units of the currency (integer, not float/double). For example, for a price of `US$
    * 1.45` pass amount = 145`. See the exp parameter in
    * [[https://core.telegram.org/bots/payments/currencies.json currencies.json]], it shows the number of digits past
    * the decimal point for each currency (2 for the majority of currencies).
    * @param invoicePayload
    *   Bot specified invoice payload
    * @return
    *   [[PreCheckoutQuery]] builder
    */
  def of(id: String, from: User, currency: String, totalAmount: Long, invoicePayload: String): PreCheckoutQuery =
    PreCheckoutQuery(
      id = id,
      from = from,
      currency = currency,
      totalAmount = totalAmount,
      invoicePayload = invoicePayload,
      shippingOptionId = None,
      orderInfo = None
    )
}

/** This object contains information about an incoming pre-checkout query.
  */
case class PreCheckoutQuery(
  id: String,
  from: User,
  currency: String,
  totalAmount: Long,
  invoicePayload: String,
  shippingOptionId: Option[String],
  orderInfo: Option[OrderInfo]
) {

  /** Identifier of the shipping option chosen by the user
    */
  def withShippingOptionId(shippingOptionId: String): PreCheckoutQuery = copy(shippingOptionId = Some(shippingOptionId))

  /** Order information provided by the user
    */
  def withOrderInfo(orderInfo: OrderInfo): PreCheckoutQuery = copy(orderInfo = Some(orderInfo))
}
