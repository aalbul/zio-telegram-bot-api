package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.PollTypes.PollType
import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
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
