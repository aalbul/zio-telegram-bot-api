package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ShippingAddress {
  implicit val shippingAddressJsonCodec: JsonValueCodec[ShippingAddress] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[ShippingAddress]]
    * @param countryCode
    *   Two-letter ISO 3166-1 alpha-2 country code
    * @param state
    *   State, if applicable
    * @param city
    *   City
    * @param streetLine1
    *   First line for the address
    * @param streetLine2
    *   Second line for the address
    * @param postCode
    *   Address post code
    * @return
    *   [[ShippingAddress]] builder
    */
  def of(
    countryCode: String,
    state: String,
    city: String,
    streetLine1: String,
    streetLine2: String,
    postCode: String
  ): ShippingAddress = ShippingAddress(
    countryCode = countryCode,
    state = state,
    city = city,
    streetLine1 = streetLine1,
    streetLine2 = streetLine2,
    postCode = postCode
  )
}

/** This object represents a shipping address.
  */
case class ShippingAddress(
  countryCode: String,
  state: String,
  city: String,
  streetLine1: String,
  streetLine2: String,
  postCode: String
)
