package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

@ConfiguredJsonCodec(encodeOnly = true)
case class KeyboardButton(
  text: String,
  requestContact: Option[Boolean],
  requestLocation: Option[Boolean],
  requestPoll: Option[KeyboardButtonPollType],
  webApp: Option[WebAppInfo]
)
