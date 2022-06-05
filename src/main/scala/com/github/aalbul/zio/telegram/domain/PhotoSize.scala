package com.github.aalbul.zio.telegram.domain

import com.pengrad.telegrambot.model.PhotoSize as LibPhotoSize

object PhotoSize {
  implicit class PhotoSizeOps(photoSize: LibPhotoSize) {
    def asScala: PhotoSize = PhotoSize(
      fileId = photoSize.fileId(),
      fileUniqueId = photoSize.fileUniqueId(),
      width = photoSize.width(),
      height = photoSize.height(),
      fileSize = Option(photoSize.fileSize())
    )
  }
}

case class PhotoSize(fileId: String, fileUniqueId: String, width: Int, height: Int, fileSize: Option[Int])
