package com.github.aalbul.zio.telegram.domain

import com.pengrad.telegrambot.passport.PassportFile as LibPassportFile

import java.time.Instant

object PassportFile {
  implicit class PassportFileOps(passportFile: LibPassportFile) {
    def asScala: PassportFile = PassportFile(
      fileId = passportFile.fileId(),
      fileUniqueId = passportFile.fileUniqueId(),
      fileSize = passportFile.fileSize(),
      fileDate = Instant.ofEpochSecond(passportFile.fileDate().toLong)
    )
  }
}

case class PassportFile(fileId: String, fileUniqueId: String, fileSize: Int, fileDate: Instant)
