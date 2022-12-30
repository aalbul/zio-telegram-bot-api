package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.EditMessageLiveLocation.EditMessageLiveLocationPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.{InlineKeyboardMarkup, Message, MessageOrInlineMessageUpdateResult}

object EditMessageLiveLocation {
  object EditMessageLiveLocationPayload {
    implicit val editMessageLiveLocationPayloadJsonCodec: JsonValueCodec[EditMessageLiveLocationPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

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

  /** Constructs minimal [[EditMessageLiveLocation]] command
    * @param latitude
    *   Latitude of new location
    * @param longitude
    *   Longitude of new location
    * @return
    *   [[EditMessageLiveLocation]] builder
    */
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

/** Use this method to edit live location messages. A location can be edited until its live_period expires or editing is
  * explicitly disabled by a call to [[StopMessageLiveLocation]]. On success, if the edited message is not an inline
  * message, the edited [[Message]] is returned, otherwise ''True'' is returned.
  */
case class EditMessageLiveLocation(payload: EditMessageLiveLocationPayload)
  extends Command[MessageOrInlineMessageUpdateResult] {
  override val name: String = "editMessageLiveLocation"

  override def parameters: ApiParameters = JsonBody(payload)

  /** Required if ''inline_message_id'' is not specified. Unique identifier for the target chat or username of the
    * target channel (in the format @channelusername)
    */
  def withChatId(chatId: String): EditMessageLiveLocation = copy(payload = payload.copy(chatId = Some(chatId)))

  /** Required if ''inline_message_id'' is not specified. Identifier of the message to edit
    */
  def withMessageId(messageId: Long): EditMessageLiveLocation =
    copy(payload = payload.copy(messageId = Some(messageId)))

  /** Required if ''chat_id'' and ''message_id'' are not specified. Identifier of the inline message
    */
  def withInlineMessageId(inlineMessageId: String): EditMessageLiveLocation =
    copy(payload = payload.copy(inlineMessageId = Some(inlineMessageId)))

  /** The radius of uncertainty for the location, measured in meters; 0-1500
    */
  def withHorizontalAccuracy(accuracy: Double): EditMessageLiveLocation =
    copy(payload = payload.copy(horizontalAccuracy = Some(accuracy)))

  /** Direction in which the user is moving, in degrees. Must be between 1 and 360 if specified.
    */
  def withHeading(heading: Long): EditMessageLiveLocation = copy(payload = payload.copy(heading = Some(heading)))

  /** The maximum distance for proximity alerts about approaching another chat member, in meters. Must be between 1 and
    * 100000 if specified.
    */
  def withProximityAlertRadius(radius: Long): EditMessageLiveLocation =
    copy(payload = payload.copy(proximityAlertRadius = Some(radius)))

  /** A JSON-serialized object for a new
    * [[https://core.telegram.org/bots#inline-keyboards-and-on-the-fly-updating inline keyboard]].
    */
  def withReplyMarkup(markup: InlineKeyboardMarkup): EditMessageLiveLocation =
    copy(payload = payload.copy(replyMarkup = Some(markup)))
}
