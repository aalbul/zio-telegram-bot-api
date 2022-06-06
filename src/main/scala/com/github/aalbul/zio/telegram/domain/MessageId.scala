package com.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(encodeOnly = true)
case class MessageId(messageId: Long)
