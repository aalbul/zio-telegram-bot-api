package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.Message.MessageOps
import com.github.aalbul.zio.telegram.domain.User.UserOps
import com.pengrad.telegrambot.model.CallbackQuery as LibCallbackQuery

object CallbackQuery {
  implicit class CallbackQueryOps(query: LibCallbackQuery) {
    def asScala: CallbackQuery = CallbackQuery(
      id = query.id(),
      from = query.from().asScala,
      message = Option(query.message()).map(_.asScala),
      inlineMessageId = Option(query.inlineMessageId()),
      chatInstance = query.chatInstance(),
      data = Option(query.data()),
      gameShortName = Option(query.gameShortName())
    )
  }
}

case class CallbackQuery(
  id: String,
  from: User,
  message: Option[Message],
  inlineMessageId: Option[String],
  chatInstance: String,
  data: Option[String],
  gameShortName: Option[String]
)
