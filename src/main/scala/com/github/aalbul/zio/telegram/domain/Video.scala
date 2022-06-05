package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.PhotoSize.PhotoSizeOps
import com.pengrad.telegrambot.model.Video as LibVideo

object Video {
  implicit class VideoOps(video: LibVideo) {
    def asScala: Video = Video(
      fileId = video.fileId(),
      fileUniqueId = video.fileUniqueId(),
      width = video.width(),
      height = video.height(),
      duration = video.duration(),
      thumb = Option(video.thumb()).map(_.asScala),
      fileName = Option(video.fileName()),
      mimeType = Option(video.mimeType()),
      fileSize = Option(video.fileSize())
    )
  }
}

case class Video(
  fileId: String,
  fileUniqueId: String,
  width: Int,
  height: Int,
  duration: Int,
  thumb: Option[PhotoSize],
  fileName: Option[String],
  mimeType: Option[String],
  fileSize: Option[Int]
)
