package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

import java.time.Instant

@ConfiguredJsonCodec(decodeOnly = true)
case class VideoChatScheduled(startDate: Instant)
