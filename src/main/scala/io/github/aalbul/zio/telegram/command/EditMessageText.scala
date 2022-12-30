package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.EditMessageText.EditMessageTextPayload
import io.github.aalbul.zio.telegram.domain.{InlineKeyboardMarkup, MessageEntity, MessageOrInlineMessageUpdateResult, ParseMode}

object EditMessageText {
  object EditMessageTextPayload {
    implicit val editMessageTextPayloadJsonCodec: JsonValueCodec[EditMessageTextPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class EditMessageTextPayload(
    chatId: Option[String],
    messageId: Option[Long],
    inlineMessageId: Option[String],
    text: String,
    parseMode: Option[ParseMode],
    entities: Option[Seq[MessageEntity]],
    disableWebPagePreview: Option[Boolean],
    replyMarkup: Option[InlineKeyboardMarkup]
  )

  /** Constructs minimal [[EditMessageText]] command
    * @param text
    *   New text of the message, 1-4096 characters after entities parsing
    * @return
    *   [[EditMessageText]] builder
    */
  def of(text: String): EditMessageText = EditMessageText(
    EditMessageTextPayload(
      chatId = None,
      messageId = None,
      inlineMessageId = None,
      text = text,
      parseMode = None,
      entities = None,
      disableWebPagePreview = None,
      replyMarkup = None
    )
  )
}

/** Use this method to edit text and [[https://core.telegram.org/bots/api#games game]] messages. On success, if the
  * edited message is not an inline message, the edited [[https://core.telegram.org/bots/api#message Message]] is
  * returned, otherwise True is returned.
  */
case class EditMessageText(payload: EditMessageTextPayload) extends Command[MessageOrInlineMessageUpdateResult] {
  override val name: String = "editMessageText"
  override def parameters: ApiParameters = JsonBody(payload)

  /** Required if inline_message_id is not specified. Unique identifier for the target chat or username of the target
    * channel (in the format @channelusername)
    */
  def withChatId(chatId: String): EditMessageText = copy(payload.copy(chatId = Some(chatId)))

  /** Required if inline_message_id is not specified. Identifier of the message to edit
    */
  def withMessageId(messageId: Long): EditMessageText = copy(payload.copy(messageId = Some(messageId)))

  /** Required if chat_id and message_id are not specified. Identifier of the inline message
    */
  def withInlineMessageId(inlineMessageId: String): EditMessageText = copy(
    payload.copy(inlineMessageId = Some(inlineMessageId))
  )

  /** Mode for parsing entities in the message text. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): EditMessageText = copy(payload.copy(parseMode = Some(parseMode)))

  /** A JSON-serialized list of special entities that appear in message text, which can be specified instead of
    * parse_mode
    */
  def withEntities(entities: Seq[MessageEntity]): EditMessageText = copy(payload.copy(entities = Some(entities)))

  /** Disables link previews for links in this message
    */
  def withDisableWebPagePreview(disableWebPagePreview: Boolean): EditMessageText = copy(
    payload.copy(disableWebPagePreview = Some(disableWebPagePreview))
  )

  /** A JSON-serialized object for an [[https://core.telegram.org/bots/features#inline-keyboards inline keyboard]].
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): EditMessageText = copy(
    payload.copy(replyMarkup = Some(replyMarkup))
  )
}
