package io.github.aalbul.zio.telegram.domain

import io.circe.Decoder

object PassportElementTypes extends Enumeration {
  implicit val passportElementDecoder: Decoder[PassportElementType] = Decoder.decodeString.map(byName)

  type PassportElementType = Value

  private lazy val indexed = values
    .map(value => value.toString.split("(?<=.)(?=\\p{Lu})").map(_.toLowerCase).mkString("_") -> value)
    .toMap

  val PersonalDetails, Passport, DriverLicense, IdentityCard, InternalPassport, Address, UtilityBill, BankStatement,
    RentalAgreement, PassportRegistration, TemporaryRegistration, PhoneNumber, Email = Value

  def byName(name: String): PassportElementType = indexed(name)
}
