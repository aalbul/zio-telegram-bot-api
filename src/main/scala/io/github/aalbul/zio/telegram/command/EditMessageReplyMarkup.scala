package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.EditMessageReplyMarkup.EditMessageReplyMarkupPayload
import io.github.aalbul.zio.telegram.domain.{InlineKeyboardMarkup, MessageOrInlineMessageUpdateResult}

object EditMessageReplyMarkup {
  object EditMessageReplyMarkupPayload {
    implicit val editMessageMediaPayloadJsonCodec: JsonValueCodec[EditMessageReplyMarkupPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class EditMessageReplyMarkupPayload(
    chatId: Option[String],
    messageId: Option[Long],
    inlineMessageId: Option[String],
    replyMarkup: Option[InlineKeyboardMarkup]
  )

  /** Constructs minimal [[EditMessageReplyMarkup]] command
    * @return
    *   [[EditMessageReplyMarkup]] builder
    */
  def of: EditMessageReplyMarkup = EditMessageReplyMarkup(
    EditMessageReplyMarkupPayload(
      chatId = None,
      messageId = None,
      inlineMessageId = None,
      replyMarkup = None
    )
  )
}

/** Use this method to edit only the reply markup of messages. On success, if the edited message is not an inline
  * message, the edited [[https://core.telegram.org/bots/api#message Message]] is returned, otherwise True is returned.
  */
case class EditMessageReplyMarkup(payload: EditMessageReplyMarkupPayload)
  extends Command[MessageOrInlineMessageUpdateResult] {
  override val name: String = "editMessageReplyMarkup"
  override def parameters: ApiParameters = JsonBody(payload)

  /** Required if inline_message_id is not specified. Unique identifier for the target chat or username of the target
    * channel (in the format @channelusername)
    */
  def withChatId(chatId: String): EditMessageReplyMarkup = copy(payload.copy(chatId = Some(chatId)))

  /** Required if inline_message_id is not specified. Identifier of the message to edit
    */
  def withMessageId(messageId: Long): EditMessageReplyMarkup = copy(payload.copy(messageId = Some(messageId)))

  /** Required if chat_id and message_id are not specified. Identifier of the inline message
    */
  def withInlineMessageId(inlineMessageId: String): EditMessageReplyMarkup = copy(
    payload.copy(inlineMessageId = Some(inlineMessageId))
  )

  /** A JSON-serialized object for an [[https://core.telegram.org/bots/features#inline-keyboards inline keyboard]].
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): EditMessageReplyMarkup = copy(
    payload.copy(replyMarkup = Some(replyMarkup))
  )
}
