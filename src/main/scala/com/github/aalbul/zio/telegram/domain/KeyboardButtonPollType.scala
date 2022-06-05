package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.PollTypes.PollType
import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(encodeOnly = true)
case class KeyboardButtonPollType(`type`: PollType)
