package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

@ConfiguredJsonCodec
case class User(
  id: Long,
  isBot: Boolean,
  firstName: String,
  lastName: Option[String],
  username: Option[String],
  languageCode: Option[String],
  canJoinGroups: Option[Boolean],
  canReadAllGroupMessages: Option[Boolean],
  supportsInlineQueries: Option[Boolean]
)
