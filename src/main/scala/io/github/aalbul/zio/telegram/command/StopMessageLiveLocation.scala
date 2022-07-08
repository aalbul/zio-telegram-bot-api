package io.github.aalbul.zio.telegram.command

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.command.StopMessageLiveLocation.StopMessageLiveLocationPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.{InlineKeyboardMarkup, LiveLocationUpdateResult, Message}

object StopMessageLiveLocation {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class StopMessageLiveLocationPayload(
    chatId: Option[String],
    messageId: Option[Long],
    inlineMessageId: Option[String],
    replyMarkup: Option[InlineKeyboardMarkup]
  )

  /** Constructs minimal [[StopMessageLiveLocation]] command
    * @return
    *   [[StopMessageLiveLocation]] builder
    */
  def of: StopMessageLiveLocation = StopMessageLiveLocation(
    StopMessageLiveLocationPayload(chatId = None, messageId = None, inlineMessageId = None, replyMarkup = None)
  )
}

/** Use this method to stop updating a live location message before ''live_period'' expires. On success, if the message
  * is not an inline message, the edited [[Message]] is returned, otherwise ''True'' is returned.
  */
case class StopMessageLiveLocation(payload: StopMessageLiveLocationPayload) extends Command[LiveLocationUpdateResult] {
  override val name: String = "stopMessageLiveLocation"

  override def parameters: ApiParameters = JsonBody(payload)

  /** Required if ''inline_message_id'' is not specified. Unique identifier for the target chat or username of the
    * target channel (in the format @channelusername)
    */
  def withChatId(chatId: String): StopMessageLiveLocation = copy(payload = payload.copy(chatId = Some(chatId)))

  /** Required if ''inline_message_id'' is not specified. Identifier of the message with live location to stop
    */
  def withMessageId(messageId: Long): StopMessageLiveLocation =
    copy(payload = payload.copy(messageId = Some(messageId)))

  /** Required if ''chat_id'' and ''message_id'' are not specified. Identifier of the inline message
    */
  def withInlineMessageId(id: String): StopMessageLiveLocation =
    copy(payload = payload.copy(inlineMessageId = Some(id)))

  /** A JSON-serialized object for a new
    * [[https://core.telegram.org/bots#inline-keyboards-and-on-the-fly-updating inline keyboard]].
    */
  def withReplyMarkup(markup: InlineKeyboardMarkup): StopMessageLiveLocation =
    copy(payload = payload.copy(replyMarkup = Some(markup)))
}
