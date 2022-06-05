package com.github.aalbul.zio.telegram.domain

import com.pengrad.telegrambot.model.MessageAutoDeleteTimerChanged as LibMessageAutoDeleteTimerChanged

object MessageAutoDeleteTimerChanged {
  implicit class MessageAutoDeleteTimerChangedOps(changed: LibMessageAutoDeleteTimerChanged) {
    def asScala: MessageAutoDeleteTimerChanged = MessageAutoDeleteTimerChanged(changed.messageAutoDeleteTime())
  }
}

case class MessageAutoDeleteTimerChanged(messageAutoDeleteTime: Int)
