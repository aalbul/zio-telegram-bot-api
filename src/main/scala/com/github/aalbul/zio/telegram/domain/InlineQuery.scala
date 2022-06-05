package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.ChatTypes.ChatType
import com.github.aalbul.zio.telegram.domain.Location.LocationOps
import com.github.aalbul.zio.telegram.domain.User.UserOps
import com.pengrad.telegrambot.model.InlineQuery as LibInlineQuery

object InlineQuery {
  implicit class InlineQueryOps(query: LibInlineQuery) {
    def asScala: InlineQuery = InlineQuery(
      id = query.id(),
      from = query.from().asScala,
      query = query.query(),
      offset = query.offset(),
      chatType = Option(ChatTypes.byName(query.chatType())),
      location = Option(query.location()).map(_.asScala)
    )
  }
}

case class InlineQuery(
  id: String,
  from: User,
  query: String,
  offset: String,
  chatType: Option[ChatType],
  location: Option[Location]
)
