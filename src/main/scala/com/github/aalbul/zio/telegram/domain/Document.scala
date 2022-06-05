package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.PhotoSize.PhotoSizeOps
import com.pengrad.telegrambot.model.Document as LibDocument

object Document {
  implicit class DocumentOps(document: LibDocument) {
    def asScala: Document = Document(
      fileId = document.fileId(),
      fileUniqueId = document.fileUniqueId(),
      thumb = Option(document.thumb()).map(_.asScala),
      fileName = Option(document.fileName()),
      mimeType = Option(document.mimeType()),
      fileSize = Option(document.fileSize())
    )
  }
}

case class Document(
  fileId: String,
  fileUniqueId: String,
  thumb: Option[PhotoSize],
  fileName: Option[String],
  mimeType: Option[String],
  fileSize: Option[Int]
)
