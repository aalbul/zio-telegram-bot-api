package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.Location.LocationOps
import com.github.aalbul.zio.telegram.domain.User.UserOps
import com.pengrad.telegrambot.model.ChosenInlineResult as LibChosenInlineResult

object ChosenInlineResult {
  implicit class ChosenInlineResultOps(result: LibChosenInlineResult) {
    def asScala: ChosenInlineResult = ChosenInlineResult(
      resultId = result.resultId,
      from = result.from.asScala,
      location = Option(result.location()).map(_.asScala),
      inlineMessageId = Option(result.inlineMessageId()),
      query = result.query()
    )
  }
}

case class ChosenInlineResult(
  resultId: String,
  from: User,
  location: Option[Location],
  inlineMessageId: Option[String],
  query: String
)
