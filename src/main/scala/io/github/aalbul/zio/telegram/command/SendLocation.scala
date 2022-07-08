package io.github.aalbul.zio.telegram.command

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.command.SendLocation.SendLocationPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.{Markup, Message}

object SendLocation {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class SendLocationPayload(
    chatId: String,
    latitude: Double,
    longitude: Double,
    horizontalAccuracy: Option[Double],
    livePeriod: Option[Long],
    heading: Option[Long],
    proximityAlertRadius: Option[Long],
    disableNotification: Option[Boolean],
    protectContent: Option[Boolean],
    replyToMessageId: Option[Long],
    allowSendingWithoutReply: Option[Boolean],
    replyMarkup: Option[Markup]
  )

  /** Constructs minimal [[SendLocation]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param latitude
    *   Latitude of the location
    * @param longitude
    *   Longitude of the location
    * @return
    *   [[SendLocation]] builder
    */
  def of(chatId: String, latitude: Double, longitude: Double): SendLocation = SendLocation(
    SendLocationPayload(
      chatId = chatId,
      latitude = latitude,
      longitude = longitude,
      horizontalAccuracy = None,
      livePeriod = None,
      heading = None,
      proximityAlertRadius = None,
      disableNotification = None,
      protectContent = None,
      replyToMessageId = None,
      allowSendingWithoutReply = None,
      replyMarkup = None
    )
  )
}

/** Use this method to send point on the map. On success, the sent [[Message]] is returned.
  */
case class SendLocation(payload: SendLocationPayload) extends Command[Message] {
  override val name: String = "sendLocation"

  override def parameters: ApiParameters = JsonBody(payload)

  /** The radius of uncertainty for the location, measured in meters; 0-1500
    */
  def withHorizontalAccuracy(accuracy: Double): SendLocation =
    copy(payload = payload.copy(horizontalAccuracy = Some(accuracy)))

  /** Period in seconds for which the location will be updated (see
    * [[https://telegram.org/blog/live-locations Live Locations]], should be between 60 and 86400.
    */
  def withLivePeriod(livePeriod: Long): SendLocation = copy(payload = payload.copy(livePeriod = Some(livePeriod)))

  /** For live locations, a direction in which the user is moving, in degrees. Must be between 1 and 360 if specified.
    */
  def withHeading(heading: Long): SendLocation = copy(payload = payload.copy(heading = Some(heading)))

  /** For live locations, a maximum distance for proximity alerts about approaching another chat member, in meters. Must
    * be between 1 and 100000 if specified.
    */
  def withProximityAlertRadius(radius: Long): SendLocation =
    copy(payload = payload.copy(proximityAlertRadius = Some(radius)))

  /** Sends the message [[https://telegram.org/blog/channels-2-0#silent-messages silently]]. Users will receive a
    * notification with no sound.
    */
  def withDisableNotification(disable: Boolean): SendLocation = copy(payload.copy(disableNotification = Some(disable)))

  /** Protects the contents of the sent message from forwarding and saving
    */
  def withProtectContent(protect: Boolean): SendLocation = copy(payload.copy(protectContent = Some(protect)))

  /** If the message is a reply, ID of the original message
    */
  def withReplyToMessageId(id: Long): SendLocation = copy(payload.copy(replyToMessageId = Some(id)))

  /** Pass ''True'', if the message should be sent even if the specified replied-to message is not found
    */
  def withAllowSendingWithoutReply(allow: Boolean): SendLocation = copy(
    payload.copy(allowSendingWithoutReply = Some(allow))
  )

  /** Additional interface options. An object for an
    * [[https://core.telegram.org/bots#inline-keyboards-and-on-the-fly-updating inline keyboard]],
    * [[https://core.telegram.org/bots#keyboards custom reply keyboard]], instructions to remove reply keyboard or to
    * force a reply from the user.
    */
  def withReplyMarkup(markup: Markup): SendLocation = copy(payload.copy(replyMarkup = Some(markup)))
}
