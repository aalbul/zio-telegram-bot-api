package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.StopMessageLiveLocation.StopMessageLiveLocationPayload
import io.github.aalbul.zio.telegram.domain.{InlineKeyboardMarkup, LiveLocationUpdateResult}
import io.circe.generic.extras.ConfiguredJsonCodec

object StopMessageLiveLocation {
  @ConfiguredJsonCodec(encodeOnly = true)
  case class StopMessageLiveLocationPayload(
    chatId: Option[String],
    messageId: Option[Long],
    inlineMessageId: Option[String],
    replyMarkup: Option[InlineKeyboardMarkup]
  )

  def of: StopMessageLiveLocation = StopMessageLiveLocation(
    StopMessageLiveLocationPayload(chatId = None, messageId = None, inlineMessageId = None, replyMarkup = None)
  )
}

case class StopMessageLiveLocation(payload: StopMessageLiveLocationPayload) extends Command[LiveLocationUpdateResult] {
  override val name: String = "stopMessageLiveLocation"

  override def parameters: ApiParameters = JsonBody(payload)

  def withChatId(chatId: String): StopMessageLiveLocation = copy(payload = payload.copy(chatId = Some(chatId)))
  def withMessageId(messageId: Long): StopMessageLiveLocation =
    copy(payload = payload.copy(messageId = Some(messageId)))
  def withInlineMessageId(id: String): StopMessageLiveLocation =
    copy(payload = payload.copy(inlineMessageId = Some(id)))
  def withReplyMarkup(markup: InlineKeyboardMarkup): StopMessageLiveLocation =
    copy(payload = payload.copy(replyMarkup = Some(markup)))
}
