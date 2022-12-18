package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ShippingAddress {
  implicit val shippingAddressJsonCodec: JsonValueCodec[ShippingAddress] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

case class ShippingAddress(
  countryCode: String,
  state: String,
  city: String,
  streetLine1: String,
  streetLine2: String,
  postCode: String
)
