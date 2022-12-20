package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object Contact {
  implicit val contactJsonCodec: JsonValueCodec[Contact] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[Contact]] object
    * @param phoneNumber
    *   Contact's phone number
    * @param firstName
    *   Contact's first name
    * @return
    *   [[Contact]] builder
    */
  def of(phoneNumber: String, firstName: String): Contact = Contact(
    phoneNumber = phoneNumber,
    firstName = firstName,
    lastName = None,
    userId = None,
    vcard = None
  )
}

/** This object represents a phone contact.
  */
case class Contact(
  phoneNumber: String,
  firstName: String,
  lastName: Option[String],
  userId: Option[Long],
  vcard: Option[String]
) {

  /** Contact's last name
    */
  def withLastName(lastName: String): Contact = copy(lastName = Some(lastName))

  /** Contact's user identifier in Telegram. This number may have more than 32 significant bits and some programming
    * languages may have difficulty/silent defects in interpreting it. But it has at most 52 significant bits, so a
    * 64-bit integer or double-precision float type are safe for storing this identifier.
    */
  def withUserId(userId: Long): Contact = copy(userId = Some(userId))

  /** Additional data about the contact in the form of a [[https://en.wikipedia.org/wiki/VCard vCard]]
    */
  def withVcard(vcard: String): Contact = copy(vcard = Some(vcard))
}
