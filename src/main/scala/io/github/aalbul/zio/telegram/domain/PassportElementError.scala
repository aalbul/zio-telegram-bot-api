package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{named, CodecMakerConfig, JsonCodecMaker}

object PassportElementError {
  implicit val passportElementErrorJsonCodec: JsonValueCodec[PassportElementError] = JsonCodecMaker.make(
    CodecMakerConfig
      .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
      .withDiscriminatorFieldName(Some("source"))
      .withRequireDiscriminatorFirst(false)
  )
}

/** This object represents an error in the Telegram Passport element which was submitted that should be resolved by the
  * user.
  */
sealed trait PassportElementError

object PassportElementErrorDataField {

  /** Constructs minimal [[PassportElementErrorDataField]]
    * @param `type`
    *   The section of the user's Telegram Passport which has the error, one of “personal_details”, “passport”,
    *   “driver_license”, “identity_card”, “internal_passport”, “address”
    * @param fieldName
    *   Name of the data field which has the error
    * @param dataHash
    *   Base64-encoded data hash
    * @param message
    *   Error message
    * @return
    *   [[PassportElementErrorDataField]] builder
    */
  def of(
    `type`: PassportElementType,
    fieldName: String,
    dataHash: String,
    message: String
  ): PassportElementErrorDataField = PassportElementErrorDataField(
    `type` = `type`,
    fieldName = fieldName,
    dataHash = dataHash,
    message = message
  )
}

/** Represents an issue in one of the data fields that was provided by the user. The error is considered resolved when
  * the field's value changes.
  */
@named("data")
case class PassportElementErrorDataField(
  `type`: PassportElementType,
  fieldName: String,
  dataHash: String,
  message: String
) extends PassportElementError

object PassportElementErrorFrontSide {

  /** Constructs minimal [[PassportElementErrorFrontSide]]
    * @param `type`
    *   The section of the user's Telegram Passport which has the issue, one of “passport”, “driver_license”,
    *   “identity_card”, “internal_passport”
    * @param fileHash
    *   Base64-encoded hash of the file with the front side of the document
    * @param message
    *   Error message
    * @return
    *   [[PassportElementErrorFrontSide]] builder
    */
  def of(`type`: PassportElementType, fileHash: String, message: String): PassportElementErrorFrontSide =
    PassportElementErrorFrontSide(
      `type` = `type`,
      fileHash = fileHash,
      message = message
    )
}

/** Represents an issue with the front side of a document. The error is considered resolved when the file with the front
  * side of the document changes.
  */
@named("front_side")
case class PassportElementErrorFrontSide(
  `type`: PassportElementType,
  fileHash: String,
  message: String
) extends PassportElementError

object PassportElementErrorReverseSide {

  /** Constructs minimal [[PassportElementErrorReverseSide]]
    * @param `type`
    *   The section of the user's Telegram Passport which has the issue, one of “driver_license”, “identity_card”
    * @param fileHash
    *   Base64-encoded hash of the file with the reverse side of the document
    * @param message
    *   Error message
    * @return
    *   [[PassportElementErrorReverseSide]] builder
    */
  def of(`type`: PassportElementType, fileHash: String, message: String): PassportElementErrorReverseSide =
    PassportElementErrorReverseSide(
      `type` = `type`,
      fileHash = fileHash,
      message = message
    )
}

/** Represents an issue with the reverse side of a document. The error is considered resolved when the file with reverse
  * side of the document changes.
  */
@named("reverse_side")
case class PassportElementErrorReverseSide(
  `type`: PassportElementType,
  fileHash: String,
  message: String
) extends PassportElementError

object PassportElementErrorSelfie {

  /** Constructs minimal [[PassportElementErrorSelfie]]
    * @param `type`
    *   The section of the user's Telegram Passport which has the issue, one of “passport”, “driver_license”,
    *   “identity_card”, “internal_passport”
    * @param fileHash
    *   Base64-encoded hash of the file with the selfie
    * @param message
    *   Error message
    * @return
    *   [[PassportElementErrorSelfie]] builder
    */
  def of(`type`: PassportElementType, fileHash: String, message: String): PassportElementErrorSelfie =
    PassportElementErrorSelfie(
      `type` = `type`,
      fileHash = fileHash,
      message = message
    )
}

/** Represents an issue with the selfie with a document. The error is considered resolved when the file with the selfie
  * changes.
  */
@named("selfie")
case class PassportElementErrorSelfie(
  `type`: PassportElementType,
  fileHash: String,
  message: String
) extends PassportElementError

object PassportElementErrorFile {

  /** Constructs minimal [[PassportElementErrorFile]]
    * @param `type`
    *   The section of the user's Telegram Passport which has the issue, one of “utility_bill”, “bank_statement”,
    *   “rental_agreement”, “passport_registration”, “temporary_registration”
    * @param fileHash
    *   Base64-encoded file hash
    * @param message
    *   Error message
    * @return
    *   [[PassportElementErrorFile]] builder
    */
  def of(`type`: PassportElementType, fileHash: String, message: String): PassportElementErrorFile =
    PassportElementErrorFile(
      `type` = `type`,
      fileHash = fileHash,
      message = message
    )
}

/** Represents an issue with a document scan. The error is considered resolved when the file with the document scan
  * changes.
  */
@named("file")
case class PassportElementErrorFile(`type`: PassportElementType, fileHash: String, message: String)
  extends PassportElementError

object PassportElementErrorFiles {

  /** Constructs minimal [[PassportElementErrorFiles]]
    * @param `type`
    *   The section of the user's Telegram Passport which has the issue, one of “utility_bill”, “bank_statement”,
    *   “rental_agreement”, “passport_registration”, “temporary_registration”
    * @param fileHashes
    *   List of base64-encoded file hashes
    * @param message
    *   Error message
    * @return
    *   [[PassportElementErrorFiles]] builder
    */
  def of(`type`: PassportElementType, fileHashes: Seq[String], message: String): PassportElementErrorFiles =
    PassportElementErrorFiles(
      `type` = `type`,
      fileHashes = fileHashes,
      message = message
    )
}

/** Represents an issue with a list of scans. The error is considered resolved when the list of files containing the
  * scans changes.
  */
@named("files")
case class PassportElementErrorFiles(`type`: PassportElementType, fileHashes: Seq[String], message: String)
  extends PassportElementError

object PassportElementErrorTranslationFile {

  /** Constructs minimal [[PassportElementErrorTranslationFile]]
    * @param `type`
    *   Type of element of the user's Telegram Passport which has the issue, one of “passport”, “driver_license”,
    *   “identity_card”, “internal_passport”, “utility_bill”, “bank_statement”, “rental_agreement”,
    *   “passport_registration”, “temporary_registration”
    * @param fileHash
    *   Base64-encoded file hash
    * @param message
    *   Error message
    * @return
    *   [[PassportElementErrorTranslationFile]] builder
    */
  def of(`type`: PassportElementType, fileHash: String, message: String): PassportElementErrorTranslationFile =
    PassportElementErrorTranslationFile(
      `type` = `type`,
      fileHash = fileHash,
      message = message
    )
}

/** Represents an issue with one of the files that constitute the translation of a document. The error is considered
  * resolved when the file changes.
  */
@named("translation_file")
case class PassportElementErrorTranslationFile(
  `type`: PassportElementType,
  fileHash: String,
  message: String
) extends PassportElementError

object PassportElementErrorTranslationFiles {

  /** Constructs minimal [[PassportElementErrorTranslationFiles]]
    * @param `type`
    *   Type of element of the user's Telegram Passport which has the issue, one of “passport”, “driver_license”,
    *   “identity_card”, “internal_passport”, “utility_bill”, “bank_statement”, “rental_agreement”,
    *   “passport_registration”, “temporary_registration”
    * @param fileHashes
    *   List of base64-encoded file hashes
    * @param message
    *   Error message
    * @return
    *   [[PassportElementErrorTranslationFiles]] builder
    */
  def of(`type`: PassportElementType, fileHashes: Seq[String], message: String): PassportElementErrorTranslationFiles =
    PassportElementErrorTranslationFiles(
      `type` = `type`,
      fileHashes = fileHashes,
      message = message
    )
}

/** Represents an issue with the translated version of a document. The error is considered resolved when a file with the
  * document translation change.
  */
@named("translation_files")
case class PassportElementErrorTranslationFiles(`type`: PassportElementType, fileHashes: Seq[String], message: String)
  extends PassportElementError

object PassportElementErrorUnspecified {

  /** Constructs minimal [[PassportElementErrorUnspecified]]
    * @param `type`
    *   Type of element of the user's Telegram Passport which has the issue
    * @param elementHash
    *   Base64-encoded element hash
    * @param message
    *   Error message
    * @return
    *   [[PassportElementErrorUnspecified]] builder
    */
  def of(`type`: PassportElementType, elementHash: String, message: String): PassportElementErrorUnspecified =
    PassportElementErrorUnspecified(
      `type` = `type`,
      elementHash = elementHash,
      message = message
    )
}

/** Represents an issue in an unspecified place. The error is considered resolved when new data is added.
  */
@named("unspecified")
case class PassportElementErrorUnspecified(`type`: PassportElementType, elementHash: String, message: String)
  extends PassportElementError
