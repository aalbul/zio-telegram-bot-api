package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.MessageEntityTypes.MessageEntityType
import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec
case class MessageEntity(
  `type`: MessageEntityType,
  offset: Int,
  length: Int,
  url: Option[String],
  user: Option[User],
  language: Option[String]
)
