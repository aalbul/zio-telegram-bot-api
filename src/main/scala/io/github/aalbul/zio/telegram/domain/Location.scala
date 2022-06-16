package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
case class Location(
  longitude: Float,
  latitude: Float,
  horizontalAccuracy: Option[Float],
  livePeriod: Option[Int],
  heading: Option[Int],
  proximityAlertRadius: Option[Int]
)
