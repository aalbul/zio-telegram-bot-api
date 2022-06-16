package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

@ConfiguredJsonCodec(encodeOnly = true)
case class ForceReply(forceReply: Boolean, inputFieldPlaceholder: Option[String], selective: Option[Boolean])
  extends Markup
