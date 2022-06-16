package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.domain.MessageEntityTypes.MessageEntityType
import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

@ConfiguredJsonCodec
case class MessageEntity(
  `type`: MessageEntityType,
  offset: Int,
  length: Int,
  url: Option[String],
  user: Option[User],
  language: Option[String]
)
