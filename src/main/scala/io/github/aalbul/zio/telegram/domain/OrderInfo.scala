package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object OrderInfo {

  /** Constructs minimal [[OrderInfo]]
    * @return
    *   [[OrderInfo]] builder
    */
  def of: OrderInfo = OrderInfo(name = None, phoneNumber = None, email = None, shippingAddress = None)

  implicit val orderInfoJsonCodec: JsonValueCodec[OrderInfo] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

/** This object represents information about an order.
  */
case class OrderInfo(
  name: Option[String],
  phoneNumber: Option[String],
  email: Option[String],
  shippingAddress: Option[ShippingAddress]
) {

  /** User name */
  def withName(name: String): OrderInfo = copy(name = Some(name))

  /** User's phone number */
  def withPhoneNumber(phoneNumber: String): OrderInfo = copy(phoneNumber = Some(phoneNumber))

  /** User email */
  def withEmail(email: String): OrderInfo = copy(email = Some(email))

  /** User shipping address */
  def withShippingAddress(shippingAddress: ShippingAddress): OrderInfo = copy(shippingAddress = Some(shippingAddress))
}
