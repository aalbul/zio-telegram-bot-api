package com.github.aalbul.zio.telegram.domain

import com.pengrad.telegrambot.model.WebAppData as LibWebAppData

object WebAppData {
  implicit class WebAppDataOps(data: LibWebAppData) {
    def asScala: WebAppData = WebAppData(data = data.data(), buttonText = data.buttonText())
  }
}

case class WebAppData(data: String, buttonText: String)
