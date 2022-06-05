package com.github.aalbul.zio.telegram.domain

import com.pengrad.telegrambot.model.ChatPhoto as LibChatPhoto

object ChatPhoto {
  implicit class ChatPhotoOps(chatPhoto: LibChatPhoto) {
    def asScala: ChatPhoto = ChatPhoto(
      smallFileId = chatPhoto.smallFileId(),
      smallFileUniqueId = chatPhoto.smallFileUniqueId(),
      bigFileId = chatPhoto.bigFileId(),
      bigFileUniqueId = chatPhoto.bigFileUniqueId()
    )
  }
}

case class ChatPhoto(smallFileId: String, smallFileUniqueId: String, bigFileId: String, bigFileUniqueId: String)
