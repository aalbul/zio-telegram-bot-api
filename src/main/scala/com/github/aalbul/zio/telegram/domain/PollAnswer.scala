package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.User.UserOps
import com.pengrad.telegrambot.model.PollAnswer as LibPollAnswer

object PollAnswer {
  implicit class PollAnswerOps(answer: LibPollAnswer) {
    def asScala: PollAnswer = PollAnswer(
      pollId = answer.pollId(),
      user = answer.user().asScala,
      optionIds = answer.optionIds().toSeq.map(_.toInt)
    )
  }
}

case class PollAnswer(pollId: String, user: User, optionIds: Seq[Int])
