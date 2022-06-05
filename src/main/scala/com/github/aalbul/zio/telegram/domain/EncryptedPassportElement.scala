package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.PassportElementTypes.PassportElementType
import com.github.aalbul.zio.telegram.domain.PassportFile.PassportFileOps
import com.pengrad.telegrambot.passport.EncryptedPassportElement as LibEncryptedPassportElement

object EncryptedPassportElement {
  implicit class EncryptedPassportElementOps(element: LibEncryptedPassportElement) {
    def asScala: EncryptedPassportElement = EncryptedPassportElement(
      `type` = PassportElementTypes.byName(element.`type`().name()),
      data = Option(element.data()),
      phoneNumber = Option(element.phoneNumber()),
      email = Option(element.email()),
      files = Option(element.files()).map(_.map(_.asScala)),
      frontSide = Option(element.frontSide()).map(_.asScala),
      reverseSide = Option(element.reverseSide()).map(_.asScala),
      selfie = Option(element.selfie()).map(_.asScala),
      translation = Option(element.translation()).map(_.map(_.asScala)),
      hash = element.hash()
    )
  }
}

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
