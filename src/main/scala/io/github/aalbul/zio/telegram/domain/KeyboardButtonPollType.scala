package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.domain.PollTypes.PollType
import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

@ConfiguredJsonCodec(encodeOnly = true)
case class KeyboardButtonPollType(`type`: PollType)
