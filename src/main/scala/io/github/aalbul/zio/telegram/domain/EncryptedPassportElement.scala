package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object EncryptedPassportElement {
  implicit val encryptedPassportElementJsonCodec: JsonValueCodec[EncryptedPassportElement] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[EncryptedPassportElement]] object
    * @param `type`
    *   Element type. One of “personal_details”, “passport”, “driver_license”, “identity_card”, “internal_passport”,
    *   “address”, “utility_bill”, “bank_statement”, “rental_agreement”, “passport_registration”,
    *   “temporary_registration”, “phone_number”, “email”.
    * @param hash
    *   Base64-encoded element hash for using in
    *   [[https://core.telegram.org/bots/api#passportelementerrorunspecified PassportElementErrorUnspecified]]
    * @return
    *   [[EncryptedPassportElement]] builder
    */
  def of(`type`: PassportElementType, hash: String): EncryptedPassportElement = EncryptedPassportElement(
    `type` = `type`,
    data = None,
    phoneNumber = None,
    email = None,
    files = None,
    frontSide = None,
    reverseSide = None,
    selfie = None,
    translation = None,
    hash = hash
  )
}

/** Describes documents or other Telegram Passport elements shared with the bot by the user.
  */
case class EncryptedPassportElement(
  `type`: PassportElementType,
  data: Option[String],
  phoneNumber: Option[String],
  email: Option[String],
  files: Option[Seq[PassportFile]],
  frontSide: Option[PassportFile],
  reverseSide: Option[PassportFile],
  selfie: Option[PassportFile],
  translation: Option[Seq[PassportFile]],
  hash: String
) {

  /** Base64-encoded encrypted Telegram Passport element data provided by the user, available for “personal_details”,
    * “passport”, “driver_license”, “identity_card”, “internal_passport” and “address” types. Can be decrypted and
    * verified using the accompanying [[https://core.telegram.org/bots/api#encryptedcredentials EncryptedCredentials]].
    */
  def withData(data: String): EncryptedPassportElement = copy(data = Some(data))

  /** User's verified phone number, available only for “phone_number” type
    */
  def withPhoneNumber(phoneNumber: String): EncryptedPassportElement = copy(phoneNumber = Some(phoneNumber))

  /** User's verified email address, available only for “email” type
    */
  def withEmail(email: String): EncryptedPassportElement = copy(email = Some(email))

  /** Array of encrypted files with documents provided by the user, available for “utility_bill”, “bank_statement”,
    * “rental_agreement”, “passport_registration” and “temporary_registration” types. Files can be decrypted and
    * verified using the accompanying [[https://core.telegram.org/bots/api#encryptedcredentials EncryptedCredentials]].
    */
  def withFiles(files: Seq[PassportFile]): EncryptedPassportElement = copy(files = Some(files))

  /** Encrypted file with the front side of the document, provided by the user. Available for “passport”,
    * “driver_license”, “identity_card” and “internal_passport”. The file can be decrypted and verified using the
    * accompanying [[https://core.telegram.org/bots/api#encryptedcredentials EncryptedCredentials]].
    */
  def withFrontSide(frontSide: PassportFile): EncryptedPassportElement = copy(frontSide = Some(frontSide))

  /** Encrypted file with the reverse side of the document, provided by the user. Available for “driver_license” and
    * “identity_card”. The file can be decrypted and verified using the accompanying
    * [[https://core.telegram.org/bots/api#encryptedcredentials EncryptedCredentials]].
    */
  def withReverseSide(reverseSide: PassportFile): EncryptedPassportElement = copy(reverseSide = Some(reverseSide))

  /** Encrypted file with the selfie of the user holding a document, provided by the user; available for “passport”,
    * “driver_license”, “identity_card” and “internal_passport”. The file can be decrypted and verified using the
    * accompanying [[https://core.telegram.org/bots/api#encryptedcredentials EncryptedCredentials]].
    */
  def withSelfie(selfie: PassportFile): EncryptedPassportElement = copy(selfie = Some(selfie))

  /** Array of encrypted files with translated versions of documents provided by the user. Available if requested for
    * “passport”, “driver_license”, “identity_card”, “internal_passport”, “utility_bill”, “bank_statement”,
    * “rental_agreement”, “passport_registration” and “temporary_registration” types. Files can be decrypted and
    * verified using the accompanying [[https://core.telegram.org/bots/api#encryptedcredentials EncryptedCredentials]].
    */
  def withTranslation(translation: Seq[PassportFile]): EncryptedPassportElement = copy(translation = Some(translation))
}
