package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.PhotoSize.PhotoSizeOps
import com.pengrad.telegrambot.model.VideoNote as LibVideoNote

object VideoNote {
  implicit class VideoNoteOps(videoNote: LibVideoNote) {
    def asScala: VideoNote = VideoNote(
      fileId = videoNote.fileId(),
      fileUniqueId = videoNote.fileUniqueId(),
      length = videoNote.length(),
      duration = videoNote.duration(),
      thumb = Option(videoNote.thumb()).map(_.asScala),
      fileSize = Option(videoNote.fileSize())
    )
  }
}

case class VideoNote(
  fileId: String,
  fileUniqueId: String,
  length: Int,
  duration: Int,
  thumb: Option[PhotoSize],
  fileSize: Option[Int]
)
