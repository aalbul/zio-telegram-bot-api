package com.github.aalbul.zio.telegram.domain

import com.pengrad.telegrambot.model.PollOption as LibPollOption

object PollOption {
  implicit class PollOptionOps(pollOption: LibPollOption) {
    def asScala: PollOption = PollOption(text = pollOption.text(), voterCount = pollOption.voterCount())
  }
}

case class PollOption(text: String, voterCount: Int)
