package io.github.aalbul.zio.telegram.command

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.command.EditMessageLiveLocation.EditMessageLiveLocationPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.{InlineKeyboardMarkup, LiveLocationUpdateResult}

object EditMessageLiveLocation {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class EditMessageLiveLocationPayload(
    latitude: Double,
    longitude: Double,
    chatId: Option[String],
    messageId: Option[Long],
    inlineMessageId: Option[String],
    horizontalAccuracy: Option[Double],
    heading: Option[Long],
    proximityAlertRadius: Option[Long],
    replyMarkup: Option[InlineKeyboardMarkup]
  )

  def of(latitude: Double, longitude: Double): EditMessageLiveLocation = EditMessageLiveLocation(
    EditMessageLiveLocationPayload(
      latitude = latitude,
      longitude = longitude,
      chatId = None,
      messageId = None,
      inlineMessageId = None,
      horizontalAccuracy = None,
      heading = None,
      proximityAlertRadius = None,
      replyMarkup = None
    )
  )
}

case class EditMessageLiveLocation(payload: EditMessageLiveLocationPayload) extends Command[LiveLocationUpdateResult] {
  override val name: String = "editMessageLiveLocation"

  override def parameters: ApiParameters = JsonBody(payload)

  def withChatId(chatId: String): EditMessageLiveLocation = copy(payload = payload.copy(chatId = Some(chatId)))
  def withMessageId(messageId: Long): EditMessageLiveLocation =
    copy(payload = payload.copy(messageId = Some(messageId)))
  def withInlineMessageId(inlineMessageId: String): EditMessageLiveLocation =
    copy(payload = payload.copy(inlineMessageId = Some(inlineMessageId)))
  def withHorizontalAccuracy(accuracy: Double): EditMessageLiveLocation =
    copy(payload = payload.copy(horizontalAccuracy = Some(accuracy)))
  def withHeading(heading: Long): EditMessageLiveLocation = copy(payload = payload.copy(heading = Some(heading)))
  def withProximityAlertRadius(radius: Long): EditMessageLiveLocation =
    copy(payload = payload.copy(proximityAlertRadius = Some(radius)))
  def withReplyMarkup(markup: InlineKeyboardMarkup): EditMessageLiveLocation =
    copy(payload = payload.copy(replyMarkup = Some(markup)))
}
