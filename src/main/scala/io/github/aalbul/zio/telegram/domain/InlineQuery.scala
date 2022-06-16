package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.domain.ChatTypes.ChatType
import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

@ConfiguredJsonCodec(decodeOnly = true)
case class InlineQuery(
  id: String,
  from: User,
  query: String,
  offset: String,
  chatType: Option[ChatType],
  location: Option[Location]
)
