package com.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

import java.time.Duration

@ConfiguredJsonCodec(decodeOnly = true)
case class VideoChatEnded(duration: Duration)
