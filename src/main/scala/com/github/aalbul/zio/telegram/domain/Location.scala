package com.github.aalbul.zio.telegram.domain

import com.pengrad.telegrambot.model.Location as LibLocation

object Location {
  implicit class LocationOps(location: LibLocation) {
    def asScala: Location = Location(
      longitude = location.longitude(),
      latitude = location.latitude(),
      horizontalAccuracy = Option(location.horizontalAccuracy()),
      livePeriod = Option(location.livePeriod()),
      heading = Option(location.heading()),
      proximityAlertRadius = Option(location.proximityAlertRadius())
    )
  }
}

case class Location(
  longitude: Float,
  latitude: Float,
  horizontalAccuracy: Option[Float],
  livePeriod: Option[Int],
  heading: Option[Int],
  proximityAlertRadius: Option[Int]
)
