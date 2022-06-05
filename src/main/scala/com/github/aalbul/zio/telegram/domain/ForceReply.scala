package com.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(encodeOnly = true)
case class ForceReply(forceReply: Boolean, inputFieldPlaceholder: Option[String], selective: Option[Boolean])
  extends Markup
