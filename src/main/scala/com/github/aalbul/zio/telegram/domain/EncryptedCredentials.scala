package com.github.aalbul.zio.telegram.domain

import com.pengrad.telegrambot.passport.EncryptedCredentials as LibEncryptedCredentials

object EncryptedCredentials {
  implicit class EncryptedCredentialsOps(credentials: LibEncryptedCredentials) {
    def asScala: EncryptedCredentials = EncryptedCredentials(
      data = credentials.data(),
      hash = credentials.hash(),
      secret = credentials.secret()
    )
  }
}

case class EncryptedCredentials(data: String, hash: String, secret: String)
