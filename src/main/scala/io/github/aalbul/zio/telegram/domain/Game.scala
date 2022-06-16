package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

@ConfiguredJsonCodec(decodeOnly = true)
case class Game(
  title: String,
  description: String,
  photo: Seq[PhotoSize],
  text: Option[String],
  textEntities: Option[Seq[MessageEntity]],
  animation: Option[Animation]
)
