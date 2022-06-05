package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.MessageEntity.MessageEntityOps
import com.github.aalbul.zio.telegram.domain.PollOption.PollOptionOps
import com.github.aalbul.zio.telegram.domain.PollTypes.PollType
import com.pengrad.telegrambot.model.Poll as LibPoll

object Poll {
  implicit class PollOps(poll: LibPoll) {
    def asScala: Poll = Poll(
      id = poll.id(),
      question = poll.question(),
      options = poll.options().map(_.asScala),
      totalVoterCount = poll.totalVoterCount(),
      isClosed = poll.isClosed,
      isAnonymous = poll.isAnonymous,
      `type` = PollTypes.byName(poll.`type`().name()),
      allowsMultipleAnswers = poll.allowsMultipleAnswers(),
      correctOptionId = Option(poll.correctOptionId()),
      explanation = Option(poll.explanation()),
      explanationEntities = Option(poll.explanationEntities()).map(_.map(_.asScala)),
      openPeriod = Option(poll.openPeriod()),
      closeDate = Option(poll.closeDate())
    )
  }
}

case class Poll(
  id: String,
  question: String,
  options: Seq[PollOption],
  totalVoterCount: Int,
  isClosed: Boolean,
  isAnonymous: Boolean,
  `type`: PollType,
  allowsMultipleAnswers: Boolean,
  correctOptionId: Option[Int],
  explanation: Option[String],
  explanationEntities: Option[Seq[MessageEntity]],
  openPeriod: Option[Int],
  closeDate: Option[Int]
)
