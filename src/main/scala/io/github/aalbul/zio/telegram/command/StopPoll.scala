package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.StopPoll.StopPollPayload
import io.github.aalbul.zio.telegram.domain.{InlineKeyboardMarkup, Poll}

object StopPoll {
  object StopPollPayload {
    implicit val stopPollPayloadJsonCodec: JsonValueCodec[StopPollPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class StopPollPayload(chatId: String, messageId: Long, replyMarkup: Option[InlineKeyboardMarkup])

  /** Constructs minimal [[StopPoll]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param messageId
    *   Identifier of the original message with the poll
    * @return
    *   [[StopPoll]] builder
    */
  def of(chatId: String, messageId: Long): StopPoll = StopPoll(
    StopPollPayload(
      chatId = chatId,
      messageId = messageId,
      replyMarkup = None
    )
  )
}

/** Use this method to stop a poll which was sent by the bot. On success, the stopped
  * [[https://core.telegram.org/bots/api#poll Poll]] is returned.
  */
case class StopPoll(payload: StopPollPayload) extends Command[Poll] {
  override val name: String = "stopPoll"
  override def parameters: ApiParameters = JsonBody(payload)

  /** A JSON-serialized object for a new message
    * [[https://core.telegram.org/bots/features#inline-keyboards inline keyboard]].
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): StopPoll = copy(payload.copy(replyMarkup = Some(replyMarkup)))
}
