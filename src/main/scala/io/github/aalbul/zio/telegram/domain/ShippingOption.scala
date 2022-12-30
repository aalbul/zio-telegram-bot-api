package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}

object ShippingOption {

  implicit val shippingOptionJsonCodec: JsonValueCodec[ShippingOption] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[ShippingOption]]
    * @param id
    *   Shipping option identifier
    * @param title
    *   Option title
    * @param prices
    *   List of price portions
    * @return
    *   [[ShippingOption]] builder
    */
  def of(id: String, title: String, prices: Seq[LabeledPrice]): ShippingOption =
    ShippingOption(id = id, title = title, prices = prices)
}

/** This object represents one shipping option.
  */
case class ShippingOption(id: String, title: String, prices: Seq[LabeledPrice])
