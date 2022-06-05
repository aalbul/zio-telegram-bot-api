package com.github.aalbul.zio.telegram.domain

import com.pengrad.telegrambot.model.Dice as LibDice

object Dice {
  implicit class DiceOps(dice: LibDice) {
    def asScala: Dice = Dice(emoji = dice.emoji(), value = dice.value())
  }
}

case class Dice(emoji: String, value: Int)
