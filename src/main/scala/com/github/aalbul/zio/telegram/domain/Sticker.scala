package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.MaskPosition.MaskPositionOps
import com.github.aalbul.zio.telegram.domain.PhotoSize.PhotoSizeOps
import com.pengrad.telegrambot.model.Sticker as LibSticker

object Sticker {
  implicit class StickerOps(sticker: LibSticker) {
    def asScala: Sticker = Sticker(
      fileId = sticker.fileId(),
      fileUniqueId = sticker.fileUniqueId(),
      width = sticker.width(),
      height = sticker.height(),
      isAnimated = sticker.isAnimated,
      isVideo = sticker.isVideo,
      thumb = Option(sticker.thumb()).map(_.asScala),
      emoji = Option(sticker.emoji()),
      setName = Option(sticker.setName()),
      maskPosition = Option(sticker.maskPosition()).map(_.asScala)
    )
  }
}

case class Sticker(
  fileId: String,
  fileUniqueId: String,
  width: Int,
  height: Int,
  isAnimated: Boolean,
  isVideo: Boolean,
  thumb: Option[PhotoSize],
  emoji: Option[String],
  setName: Option[String],
  maskPosition: Option[MaskPosition]
)
