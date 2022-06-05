package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.InlineKeyboardButton.InlineKeyboardButtonOps
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup as LibInlineKeyboardMarkup

object InlineKeyboardMarkup {
  implicit class InlineKeyboardMarkupOps(markup: LibInlineKeyboardMarkup) {
    def asScala: InlineKeyboardMarkup =
      InlineKeyboardMarkup(inlineKeyboard = markup.inlineKeyboard().toSeq.map(_.toSeq.map(_.asScala)))
  }
}

case class InlineKeyboardMarkup(inlineKeyboard: Seq[Seq[InlineKeyboardButton]])
