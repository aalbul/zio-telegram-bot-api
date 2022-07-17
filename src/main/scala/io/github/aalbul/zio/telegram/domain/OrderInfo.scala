package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object OrderInfo {

  /** Constructs minimal [[OrderInfo]]
    * @return
    *   [[OrderInfo]] builder
    */
  def of(): OrderInfo = OrderInfo(name = None, phoneNumber = None, email = None, shippingAddress = None)
}

@ConfiguredJsonCodec(decodeOnly = true)
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
