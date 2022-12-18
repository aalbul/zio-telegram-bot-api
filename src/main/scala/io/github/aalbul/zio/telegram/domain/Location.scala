package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object Location {

  /** Constructs minimal [[Location]]
    *
    * @param longitude
    *   Longitude as defined by sender
    * @param latitude
    *   Latitude as defined by sender
    *
    * @return
    *   [[Location]] builder
    */
  def of(longitude: Double, latitude: Double): Location = Location(
    longitude = longitude,
    latitude = latitude,
    horizontalAccuracy = None,
    livePeriod = None,
    heading = None,
    proximityAlertRadius = None
  )

  implicit val locationJsonCodec: JsonValueCodec[Location] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

/** This object represents a point on the map. */
case class Location(
  longitude: Double,
  latitude: Double,
  horizontalAccuracy: Option[Double],
  livePeriod: Option[Int],
  heading: Option[Int],
  proximityAlertRadius: Option[Int]
) {

  /** The radius of uncertainty for the location, measured in meters; 0-1500 */
  def withHorizontalAccuracy(accuracy: Double): Location = copy(horizontalAccuracy = Some(accuracy))

  /** Time relative to the message sending date, during which the location can be updated; in seconds. For active live
    * locations only.
    */
  def withLivePeriod(period: Int): Location = copy(livePeriod = Some(period))

  /** The direction in which user is moving, in degrees; 1-360. For active live locations only. */
  def withHeading(heading: Int): Location = copy(heading = Some(heading))

  /** The maximum distance for proximity alerts about approaching another chat member, in meters. For sent live
    * locations only.
    */
  def withProximityAlertRadius(radius: Int): Location = copy(proximityAlertRadius = Some(radius))
}
