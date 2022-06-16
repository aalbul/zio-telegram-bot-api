package io.github.aalbul.zio.telegram.domain

import io.circe.Decoder
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object PassportElementTypes extends Enumeration {
  implicit val passportElementDecoder: Decoder[PassportElementType] = Decoder.decodeString.map(byName)

  type PassportElementType = Value

  private lazy val indexed = values
    .map(value => value.toString.camelToSnakeCase -> value)
    .toMap

  val PersonalDetails, Passport, DriverLicense, IdentityCard, InternalPassport, Address, UtilityBill, BankStatement,
    RentalAgreement, PassportRegistration, TemporaryRegistration, PhoneNumber, Email = Value

  def byName(name: String): PassportElementType = indexed(name)
}
