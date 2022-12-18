package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import enumeratum.{EnumEntry, *}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

sealed trait PassportElementType extends EnumEntry

object PassportElementType extends Enum[PassportElementType] {
  case object PersonalDetails extends PassportElementType
  case object Passport extends PassportElementType
  case object DriverLicense extends PassportElementType
  case object IdentityCard extends PassportElementType
  case object InternalPassport extends PassportElementType
  case object Address extends PassportElementType
  case object UtilityBill extends PassportElementType
  case object BankStatement extends PassportElementType
  case object RentalAgreement extends PassportElementType
  case object PassportRegistration extends PassportElementType
  case object TemporaryRegistration extends PassportElementType
  case object PhoneNumber extends PassportElementType
  case object Email extends PassportElementType

  override def values: IndexedSeq[PassportElementType] = findValues

  implicit val passportElementTypeJsonCodec: JsonValueCodec[PassportElementType] = codecs.makeEnumCodec(values)
}
