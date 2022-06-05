package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.ChatTypes.ChatType
import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
case class InlineQuery(
  id: String,
  from: User,
  query: String,
  offset: String,
  chatType: Option[ChatType],
  location: Option[Location]
)
