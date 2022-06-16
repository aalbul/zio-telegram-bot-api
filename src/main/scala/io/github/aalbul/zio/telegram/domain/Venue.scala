package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
case class Venue(
  location: Location,
  title: String,
  address: String,
  foursquareId: Option[String],
  foursquareType: Option[String],
  googlePlaceId: Option[String],
  googlePlaceType: Option[String]
)
