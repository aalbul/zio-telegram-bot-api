package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.SendVenue.SendVenuePayload
import io.github.aalbul.zio.telegram.domain.{Markup, Message}
import io.circe.generic.extras.ConfiguredJsonCodec

object SendVenue {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class SendVenuePayload(
    chatId: String,
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

  def of(chatId: String, latitude: Double, longitude: Double, title: String, address: String): SendVenue = SendVenue(
    SendVenuePayload(
      chatId = chatId,
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

case class SendVenue(payload: SendVenuePayload) extends Command[Message] {
  override val name: String = "sendVenue"

  override def parameters: ApiParameters = JsonBody(payload)

  def withFoursquareId(foursquareId: String): SendVenue = copy(payload.copy(foursquareId = Some(foursquareId)))
  def withFoursquareType(foursquareType: String): SendVenue = copy(payload.copy(foursquareType = Some(foursquareType)))
  def withGooglePlaceId(googlePlaceId: String): SendVenue = copy(payload.copy(googlePlaceId = Some(googlePlaceId)))
  def withGooglePlaceType(googlePlaceType: String): SendVenue = copy(
    payload.copy(googlePlaceType = Some(googlePlaceType))
  )
  def withDisableNotification(disable: Boolean): SendVenue = copy(payload.copy(disableNotification = Some(disable)))
  def withProtectContent(protect: Boolean): SendVenue = copy(payload.copy(protectContent = Some(protect)))
  def withReplyToMessageId(id: Long): SendVenue = copy(payload.copy(replyToMessageId = Some(id)))
  def withAllowSendingWithoutReply(allow: Boolean): SendVenue = copy(
    payload.copy(allowSendingWithoutReply = Some(allow))
  )
  def withReplyMarkup(markup: Markup): SendVenue = copy(payload.copy(replyMarkup = Some(markup)))
}
