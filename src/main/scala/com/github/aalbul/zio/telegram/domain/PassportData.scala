package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.EncryptedCredentials.EncryptedCredentialsOps
import com.github.aalbul.zio.telegram.domain.EncryptedPassportElement.EncryptedPassportElementOps
import com.pengrad.telegrambot.passport.PassportData as LibPassportData

object PassportData {
  implicit class PassportDataOps(passportData: LibPassportData) {
    def asScala: PassportData = PassportData(
      data = passportData.data().map(_.asScala),
      credentials = passportData.credentials().asScala
    )
  }
}

case class PassportData(data: Seq[EncryptedPassportElement], credentials: EncryptedCredentials)
