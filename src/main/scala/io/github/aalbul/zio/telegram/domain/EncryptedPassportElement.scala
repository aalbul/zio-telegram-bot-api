package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.PassportElementTypes.PassportElementType

@ConfiguredJsonCodec(decodeOnly = true)
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
)
