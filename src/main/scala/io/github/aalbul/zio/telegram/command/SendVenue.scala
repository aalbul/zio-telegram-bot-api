package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.SendVenue.SendVenuePayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.{Markup, Message}

object SendVenue {
  object SendVenuePayload {
    implicit val sendVenuePayloadJsonCodec: JsonValueCodec[SendVenuePayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class SendVenuePayload(
    chatId: String,
    messageThreadId: Option[Long],
    latitude: Double,
    longitude: Double,
    title: String,
    address: String,
    foursquareId: Option[String],
    foursquareType: Option[String],
    googlePlaceId: Option[String],
    googlePlaceType: Option[String],
    disableNotification: Option[Boolean],
    protectContent: Option[Boolean],
    replyToMessageId: Option[Long],
    allowSendingWithoutReply: Option[Boolean],
    replyMarkup: Option[Markup]
  )

  /** Constructs minimal [[SendVenue]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param latitude
    *   Latitude of the venue
    * @param longitude
    *   Longitude of the venue
    * @param title
    *   Name of the venue
    * @param address
    *   Address of the venue
    * @return
    *   [[SendVenue]] builder
    */
  def of(chatId: String, latitude: Double, longitude: Double, title: String, address: String): SendVenue = SendVenue(
    SendVenuePayload(
      chatId = chatId,
      messageThreadId = None,
      latitude = latitude,
      longitude = longitude,
      title = title,
      address = address,
      foursquareId = None,
      foursquareType = None,
      googlePlaceId = None,
      googlePlaceType = None,
      disableNotification = None,
      protectContent = None,
      replyToMessageId = None,
      allowSendingWithoutReply = None,
      replyMarkup = None
    )
  )
}

/** Use this method to send information about a venue. On success, the sent [[Message]] is returned.
  */
case class SendVenue(payload: SendVenuePayload) extends Command[Message] {
  override val name: String = "sendVenue"

  override def parameters: ApiParameters = JsonBody(payload)

  /** Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
    */
  def withMessageThreadId(messageThreadId: Long): SendVenue = copy(
    payload.copy(messageThreadId = Some(messageThreadId))
  )

  /** Foursquare identifier of the venue
    */
  def withFoursquareId(foursquareId: String): SendVenue = copy(payload.copy(foursquareId = Some(foursquareId)))

  /** Foursquare type of the venue, if known. (For example, “arts_entertainment/default”, “arts_entertainment/aquarium”
    * or “food/icecream”.)
    */
  def withFoursquareType(foursquareType: String): SendVenue = copy(payload.copy(foursquareType = Some(foursquareType)))

  /** Google Places identifier of the venue
    */
  def withGooglePlaceId(googlePlaceId: String): SendVenue = copy(payload.copy(googlePlaceId = Some(googlePlaceId)))

  /** Google Places type of the venue. (See
    * [[https://developers.google.com/maps/documentation/places/web-service/supported_types supported types]].)
    */
  def withGooglePlaceType(googlePlaceType: String): SendVenue = copy(
    payload.copy(googlePlaceType = Some(googlePlaceType))
  )

  /** Sends the message [[https://telegram.org/blog/channels-2-0#silent-messages silently]]. Users will receive a
    * notification with no sound.
    */
  def withDisableNotification(disable: Boolean): SendVenue = copy(payload.copy(disableNotification = Some(disable)))

  /** Protects the contents of the sent message from forwarding and saving
    */
  def withProtectContent(protect: Boolean): SendVenue = copy(payload.copy(protectContent = Some(protect)))

  /** If the message is a reply, ID of the original message
    */
  def withReplyToMessageId(id: Long): SendVenue = copy(payload.copy(replyToMessageId = Some(id)))

  /** Pass ''True'', if the message should be sent even if the specified replied-to message is not found
    */
  def withAllowSendingWithoutReply(allow: Boolean): SendVenue = copy(
    payload.copy(allowSendingWithoutReply = Some(allow))
  )

  /** Additional interface options. An object for an
    * [[https://core.telegram.org/bots#inline-keyboards-and-on-the-fly-updating inline keyboard]],
    * [[https://core.telegram.org/bots#keyboards custom reply keyboard]], instructions to remove reply keyboard or to
    * force a reply from the user.
    */
  def withReplyMarkup(markup: Markup): SendVenue = copy(payload.copy(replyMarkup = Some(markup)))
}
