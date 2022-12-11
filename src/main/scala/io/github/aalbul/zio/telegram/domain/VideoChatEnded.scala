package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import scala.concurrent.duration.Duration

@ConfiguredJsonCodec(decodeOnly = true)
case class VideoChatEnded(duration: Duration)
