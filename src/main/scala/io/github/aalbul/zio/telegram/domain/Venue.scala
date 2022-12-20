package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object Venue {
  implicit val venueJsonCodec: JsonValueCodec[Venue] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[Venue]]
    * @param location
    *   Venue location. Can't be a live location
    * @param title
    *   Name of the venue
    * @param address
    *   Address of the venue
    * @return
    *   [[Venue]] builder
    */
  def of(location: Location, title: String, address: String): Venue = Venue(
    location = location,
    title = title,
    address = address,
    foursquareId = None,
    foursquareType = None,
    googlePlaceId = None,
    googlePlaceType = None
  )
}

/** This object represents a venue.
  */
case class Venue(
  location: Location,
  title: String,
  address: String,
  foursquareId: Option[String],
  foursquareType: Option[String],
  googlePlaceId: Option[String],
  googlePlaceType: Option[String]
) {

  /** Foursquare identifier of the venue
    */
  def withFoursquareId(foursquareId: String): Venue = copy(foursquareId = Some(foursquareId))

  /** Foursquare type of the venue. (For example, “arts_entertainment/default”, “arts_entertainment/aquarium” or
    * “food/icecream”.)
    */
  def withFoursquareType(foursquareType: String): Venue = copy(foursquareType = Some(foursquareType))

  /** Google Places identifier of the venue
    */
  def withGooglePlaceId(googlePlaceId: String): Venue = copy(googlePlaceId = Some(googlePlaceId))

  /** Google Places type of the venue. (See
    * [[https://developers.google.com/places/web-service/supported_types supported types]].)
    */
  def withGooglePlaceType(googlePlaceType: String): Venue = copy(googlePlaceType = Some(googlePlaceType))
}
