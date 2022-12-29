package io.github.aalbul.zio.telegram.domain

object InlineQueryResultVenue {

  /** Constructs minimal [[InlineQueryResultVenue]]
    * @param id
    *   Unique identifier for this result, 1-64 Bytes
    * @param latitude
    *   Latitude of the venue location in degrees
    * @param longitude
    *   Longitude of the venue location in degrees
    * @param title
    *   Title of the venue
    * @param address
    *   Address of the venue
    * @return
    *   [[InlineQueryResultVenue]] builder
    */
  def of(id: String, latitude: Double, longitude: Double, title: String, address: String): InlineQueryResultVenue =
    InlineQueryResultVenue(
      id = id,
      latitude = latitude,
      longitude = longitude,
      title = title,
      address = address,
      foursquareId = None,
      foursquareType = None,
      googlePlaceId = None,
      googlePlaceType = None,
      replyMarkup = None,
      inputMessageContent = None,
      thumbUrl = None,
      thumbWidth = None,
      thumbHeight = None
    )
}

/** Represents a venue. By default, the venue will be sent by the user. Alternatively, you can use input_message_content
  * to send a message with the specified content instead of the venue.
  *
  * Note: This will only work in Telegram versions released after 9 April, 2016. Older clients will ignore them.
  */
case class InlineQueryResultVenue(
  id: String,
  latitude: Double,
  longitude: Double,
  title: String,
  address: String,
  foursquareId: Option[String],
  foursquareType: Option[String],
  googlePlaceId: Option[String],
  googlePlaceType: Option[String],
  replyMarkup: Option[InlineKeyboardMarkup],
  inputMessageContent: Option[InputMessageContent],
  thumbUrl: Option[String],
  thumbWidth: Option[Long],
  thumbHeight: Option[Long],
  override val `type`: String = "venue"
) extends InlineQueryResult {

  /** Foursquare identifier of the venue if known
    */
  def withFoursquareId(foursquareId: String): InlineQueryResultVenue = copy(foursquareId = Some(foursquareId))

  /** Foursquare type of the venue. (For example, “arts_entertainment/default”, “arts_entertainment/aquarium” or
    * “food/icecream”.)
    */
  def withFoursquareType(foursquareType: String): InlineQueryResultVenue = copy(foursquareType = Some(foursquareType))

  /** Google Places identifier of the venue
    */
  def withGooglePlaceId(googlePlaceId: String): InlineQueryResultVenue = copy(googlePlaceId = Some(googlePlaceId))

  /** Google Places type of the venue. (See
    * [[https://developers.google.com/places/web-service/supported_types supported types]].)
    */
  def withGooglePlaceType(googlePlaceType: String): InlineQueryResultVenue =
    copy(googlePlaceType = Some(googlePlaceType))

  /** [[https://core.telegram.org/bots/features#inline-keyboards Inline keyboard]] attached to the message
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): InlineQueryResultVenue =
    copy(replyMarkup = Some(replyMarkup))

  /** Content of the message to be sent instead of the venue
    */
  def withInputMessageContent(inputMessageContent: InputMessageContent): InlineQueryResultVenue =
    copy(inputMessageContent = Some(inputMessageContent))

  /** Url of the thumbnail for the result
    */
  def withThumbUrl(thumbUrl: String): InlineQueryResultVenue = copy(thumbUrl = Some(thumbUrl))

  /** Thumbnail width
    */
  def withThumbWidth(thumbWidth: Long): InlineQueryResultVenue = copy(thumbWidth = Some(thumbWidth))

  /** Thumbnail height
    */
  def withThumbHeight(thumbHeight: Long): InlineQueryResultVenue = copy(thumbHeight = Some(thumbHeight))
}
