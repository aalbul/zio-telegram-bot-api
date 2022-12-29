package io.github.aalbul.zio.telegram.domain

import scala.concurrent.duration.Duration

object InlineQueryResultLocation {

  /** Constructs minimal [[InlineQueryResultLocation]]
    * @param id
    *   Unique identifier for this result, 1-64 Bytes
    * @param latitude
    *   Location latitude in degrees
    * @param longitude
    *   Location longitude in degrees
    * @param title
    *   Location title
    * @return
    *   [[InlineQueryResultLocation]] builder
    */
  def of(id: String, latitude: Double, longitude: Double, title: String): InlineQueryResultLocation =
    InlineQueryResultLocation(
      id = id,
      latitude = latitude,
      longitude = longitude,
      title = title,
      horizontalAccuracy = None,
      livePeriod = None,
      heading = None,
      proximityAlertRadius = None,
      replyMarkup = None,
      inputMessageContent = None,
      thumbUrl = None,
      thumbWidth = None,
      thumbHeight = None
    )
}

/** Represents a location on a map. By default, the location will be sent by the user. Alternatively, you can use
  * input_message_content to send a message with the specified content instead of the location.
  *
  * Note: This will only work in Telegram versions released after 9 April, 2016. Older clients will ignore them.
  */
case class InlineQueryResultLocation(
  id: String,
  latitude: Double,
  longitude: Double,
  title: String,
  horizontalAccuracy: Option[Double],
  livePeriod: Option[Duration],
  heading: Option[Long],
  proximityAlertRadius: Option[Long],
  replyMarkup: Option[InlineKeyboardMarkup],
  inputMessageContent: Option[InputMessageContent],
  thumbUrl: Option[String],
  thumbWidth: Option[Long],
  thumbHeight: Option[Long],
  override val `type`: String = "location"
) extends InlineQueryResult {

  /** The radius of uncertainty for the location, measured in meters; 0-1500
    */
  def withHorizontalAccuracy(accuracy: Double): InlineQueryResultLocation =
    copy(horizontalAccuracy = Some(accuracy))

  /** Period in seconds for which the location can be updated, should be between 60 and 86400.
    */
  def withLivePeriod(livePeriod: Duration): InlineQueryResultLocation = copy(livePeriod = Some(livePeriod))

  /** For live locations, a direction in which the user is moving, in degrees. Must be between 1 and 360 if specified.
    */
  def withHeading(heading: Long): InlineQueryResultLocation = copy(heading = Some(heading))

  /** For live locations, a maximum distance for proximity alerts about approaching another chat member, in meters. Must
    * be between 1 and 100000 if specified.
    */
  def withProximityAlertRadius(proximityAlertRadius: Long): InlineQueryResultLocation =
    copy(proximityAlertRadius = Some(proximityAlertRadius))

  /** [[https://core.telegram.org/bots/features#inline-keyboards Inline keyboard]] attached to the message
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): InlineQueryResultLocation =
    copy(replyMarkup = Some(replyMarkup))

  /** Content of the message to be sent instead of the location
    */
  def withInputMessageContent(inputMessageContent: InputMessageContent): InlineQueryResultLocation =
    copy(inputMessageContent = Some(inputMessageContent))

  /** Url of the thumbnail for the result
    */
  def withThumbUrl(thumbUrl: String): InlineQueryResultLocation = copy(thumbUrl = Some(thumbUrl))

  /** Thumbnail width
    */
  def withThumbWidth(thumbWidth: Long): InlineQueryResultLocation = copy(thumbWidth = Some(thumbWidth))

  /** Thumbnail height
    */
  def withThumbHeight(thumbHeight: Long): InlineQueryResultLocation = copy(thumbHeight = Some(thumbHeight))
}
