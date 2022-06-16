package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

@ConfiguredJsonCodec(decodeOnly = true)
case class Location(
  longitude: Float,
  latitude: Float,
  horizontalAccuracy: Option[Float],
  livePeriod: Option[Int],
  heading: Option[Int],
  proximityAlertRadius: Option[Int]
)
