package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.Location.LocationOps
import com.pengrad.telegrambot.model.ChatLocation as LibChatLocation

object ChatLocation {
  implicit class ChatLocationOps(location: LibChatLocation) {
    def asScala: ChatLocation = ChatLocation(
      location = location.location().asScala,
      address = location.address()
    )
  }
}

case class ChatLocation(location: Location, address: String)
