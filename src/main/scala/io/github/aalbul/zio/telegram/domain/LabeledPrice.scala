package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}

object LabeledPrice {
  implicit val labeledPriceJsonCodec: JsonValueCodec[LabeledPrice] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[LabeledPrice]]
    * @param label
    *   Portion label
    * @param amount
    *   Price of the product in the smallest units of the
    *   [[https://core.telegram.org/bots/payments#supported-currencies currency]] (integer, not float/double). For
    *   example, for a price of `US$ 1.45` pass `amount = 145`. See the exp parameter in
    *   [[https://core.telegram.org/bots/payments/currencies.json currencies.json]], it shows the number of digits past
    *   the decimal point for each currency (2 for the majority of currencies).
    * @return
    */
  def of(label: String, amount: Long): LabeledPrice = LabeledPrice(label = label, amount = amount)
}

/** This object represents a portion of the price for goods or services.
  */
case class LabeledPrice(label: String, amount: Long)
