package com.github.aalbul.zio.telegram.domain

import com.pengrad.telegrambot.model.Voice as LibVoice

object Voice {
  implicit class VoiceOps(voice: LibVoice) {
    def asScala: Voice = Voice(
      fileId = voice.fileId(),
      fileUniqueId = voice.fileUniqueId(),
      duration = voice.duration(),
      mimeType = Option(voice.mimeType()),
      fileSize = Option(voice.fileSize())
    )
  }
}

case class Voice(fileId: String, fileUniqueId: String, duration: Int, mimeType: Option[String], fileSize: Option[Int])
