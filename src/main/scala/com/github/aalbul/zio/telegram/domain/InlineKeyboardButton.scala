package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.WebAppInfo.WebAppInfoOps
import com.pengrad.telegrambot.model.request.InlineKeyboardButton as LibInlineKeyboardButton

object InlineKeyboardButton {
  implicit class InlineKeyboardButtonOps(button: LibInlineKeyboardButton) {
    def asScala: InlineKeyboardButton = InlineKeyboardButton(
      text = button.text(),
      url = Option(button.url()),
      callbackData = Option(button.callbackData()),
      webApp = Option(button.webApp()).map(_.asScala),
      switchInlineQuery = Option(button.switchInlineQuery()),
      switchInlineQueryCurrentChat = Option(button.switchInlineQueryCurrentChat()),
      callbackGame = Option(button.callbackGame()).map(_ => CallbackGame),
      pay = Option(button.isPay())
    )
  }
}

case class InlineKeyboardButton(
  text: String,
  url: Option[String],
  callbackData: Option[String],
  webApp: Option[WebAppInfo],
  switchInlineQuery: Option[String],
  switchInlineQueryCurrentChat: Option[String],
  callbackGame: Option[CallbackGame.type],
  pay: Option[Boolean]
)
