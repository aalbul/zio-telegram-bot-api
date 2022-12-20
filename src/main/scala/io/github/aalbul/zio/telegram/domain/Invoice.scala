package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object Invoice {
  implicit val invoiceJsonCodec: JsonValueCodec[Invoice] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[Invoice]]
    * @param title
    *   Product name
    * @param description
    *   Product description
    * @param startParameter
    *   Unique bot deep-linking parameter that can be used to generate this invoice
    * @param currency
    *   Three-letter ISO 4217 [[https://core.telegram.org/bots/payments#supported-currencies currency]] code
    * @param totalAmount
    *   Total price in the smallest units of the currency (integer, not float/double). For example, for a price of `US$
    * 1.45` pass `amount = 145`. See the exp parameter in currencies.json, it shows the number of digits past the
    * decimal point for each currency (2 for the majority of currencies).
    * @return
    *   [[Invoice]] builder
    */
  def of(title: String, description: String, startParameter: String, currency: String, totalAmount: Long): Invoice =
    Invoice(
      title = title,
      description = description,
      startParameter = startParameter,
      currency = currency,
      totalAmount = totalAmount
    )
}

/** This object contains basic information about an invoice.
  */
case class Invoice(title: String, description: String, startParameter: String, currency: String, totalAmount: Long)
