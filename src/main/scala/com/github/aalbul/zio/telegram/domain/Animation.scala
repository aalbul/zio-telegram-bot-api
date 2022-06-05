package com.github.aalbul.zio.telegram.domain

import com.pengrad.telegrambot.model.Animation as LibAnimation
import PhotoSize.PhotoSizeOps

object Animation {
  implicit class AnimationOps(animation: LibAnimation) {
    def asScala: Animation = Animation(
      fileId = animation.fileId(),
      fileUniqueId = animation.fileUniqueId(),
      width = animation.width(),
      height = animation.height(),
      duration = animation.duration(),
      thumb = Option(animation.thumb()).map(_.asScala),
      fileName = Option(animation.fileName()),
      mimeType = Option(animation.mimeType()),
      fileSize = Option(animation.fileSize())
    )
  }
}

case class Animation(
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
