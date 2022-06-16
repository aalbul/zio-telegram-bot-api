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

case class SendLocation(payload: SendLocationPayload) extends Command[Message] {
  override val name: String = "sendLocation"

  override def parameters: ApiParameters = JsonBody(payload)

  def withHorizontalAccuracy(accuracy: Double): SendLocation =
    copy(payload = payload.copy(horizontalAccuracy = Some(accuracy)))
  def withLivePeriod(livePeriod: Long): SendLocation = copy(payload = payload.copy(livePeriod = Some(livePeriod)))
  def withHeading(heading: Long): SendLocation = copy(payload = payload.copy(heading = Some(heading)))
  def withProximityAlertRadius(radius: Long): SendLocation =
    copy(payload = payload.copy(proximityAlertRadius = Some(radius)))
  def withDisableNotification(disable: Boolean): SendLocation = copy(payload.copy(disableNotification = Some(disable)))
  def withProtectContent(protect: Boolean): SendLocation = copy(payload.copy(protectContent = Some(protect)))
  def withReplyToMessageId(id: Long): SendLocation = copy(payload.copy(replyToMessageId = Some(id)))
  def withAllowSendingWithoutReply(allow: Boolean): SendLocation = copy(
    payload.copy(allowSendingWithoutReply = Some(allow))
  )
  def withReplyMarkup(markup: Markup): SendLocation = copy(payload.copy(replyMarkup = Some(markup)))
}
