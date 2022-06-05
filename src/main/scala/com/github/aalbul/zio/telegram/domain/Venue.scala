package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.Location.LocationOps
import com.pengrad.telegrambot.model.Venue as LibVenue

object Venue {
  implicit class VenueOps(venue: LibVenue) {
    def asScala: Venue = Venue(
      location = venue.location().asScala,
      title = venue.title(),
      address = venue.address(),
      foursquareId = Option(venue.foursquareId()),
      foursquareType = Option(venue.foursquareType()),
      googlePlaceId = Option(venue.googlePlaceId()),
      googlePlaceType = Option(venue.googlePlaceType())
    )
  }
}

case class Venue(
  location: Location,
  title: String,
  address: String,
  foursquareId: Option[String],
  foursquareType: Option[String],
  googlePlaceId: Option[String],
  googlePlaceType: Option[String]
)
