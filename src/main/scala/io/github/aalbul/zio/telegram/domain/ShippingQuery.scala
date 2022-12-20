package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ShippingQuery {
  implicit val shippingQueryJsonCodec: JsonValueCodec[ShippingQuery] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[ShippingQuery]]
    * @param id
    *   Unique query identifier
    * @param from
    *   User who sent the query
    * @param invoicePayload
    *   Bot specified invoice payload
    * @param shippingAddress
    *   User specified shipping address
    * @return
    *   [[ShippingQuery]] builder
    */
  def of(id: String, from: User, invoicePayload: String, shippingAddress: ShippingAddress): ShippingQuery =
    ShippingQuery(
      id = id,
      from = from,
      invoicePayload = invoicePayload,
      shippingAddress = shippingAddress
    )
}

/** This object contains information about an incoming shipping query.
  */
case class ShippingQuery(id: String, from: User, invoicePayload: String, shippingAddress: ShippingAddress)
