package com.github.aalbul.zio.telegram.domain

import com.pengrad.telegrambot.model.WebAppInfo as LibWebAppInfo

object WebAppInfo {
  implicit class WebAppInfoOps(info: LibWebAppInfo) {
    def asScala: WebAppInfo = WebAppInfo(url = info.url())
  }
}

case class WebAppInfo(url: String)
