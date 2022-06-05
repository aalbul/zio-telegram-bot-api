package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.PhotoSize.PhotoSizeOps
import com.pengrad.telegrambot.model.Audio as LibAudio

object Audio {
  implicit class AudioOps(audio: LibAudio) {
    def asScala: Audio = Audio(
      fileId = audio.fileId(),
      fileUniqueId = audio.fileUniqueId(),
      duration = audio.duration(),
      performer = Option(audio.performer()),
      title = Option(audio.title()),
      fileName = Option(audio.fileName()),
      mimeType = Option(audio.mimeType()),
      fileSize = Option(audio.fileSize()),
      thumb = Option(audio.thumb()).map(_.asScala)
    )
  }
}

case class Audio(
  fileId: String,
  fileUniqueId: String,
  duration: Int,
  performer: Option[String],
  title: Option[String],
  fileName: Option[String],
  mimeType: Option[String],
  fileSize: Option[Int],
  thumb: Option[PhotoSize]
)
