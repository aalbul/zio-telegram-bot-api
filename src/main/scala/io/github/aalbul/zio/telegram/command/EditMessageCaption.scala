package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.EditMessageCaption.EditMessageCaptionPayload
import io.github.aalbul.zio.telegram.domain.{InlineKeyboardMarkup, MessageEntity, MessageOrInlineMessageUpdateResult, ParseMode}

object EditMessageCaption {
  object EditMessageCaptionPayload {
    implicit val editMessageCaptionPayloadJsonCodec: JsonValueCodec[EditMessageCaptionPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class EditMessageCaptionPayload(
    chatId: Option[String],
    messageId: Option[Long],
    inlineMessageId: Option[String],
    caption: Option[String],
    parseMode: Option[ParseMode],
    captionEntities: Option[Seq[MessageEntity]],
    replyMarkup: Option[InlineKeyboardMarkup]
  )

  /** Constructs minimal [[EditMessageCaption]] command
    * @return
    *   [[EditMessageCaption]] builder
    */
  def of: EditMessageCaption = EditMessageCaption(
    EditMessageCaptionPayload(
      chatId = None,
      messageId = None,
      inlineMessageId = None,
      caption = None,
      parseMode = None,
      captionEntities = None,
      replyMarkup = None
    )
  )
}

/** Use this method to edit captions of messages. On success, if the edited message is not an inline message, the edited
  * [[https://core.telegram.org/bots/api#message Message]] is returned, otherwise True is returned.
  */
case class EditMessageCaption(payload: EditMessageCaptionPayload) extends Command[MessageOrInlineMessageUpdateResult] {
  override val name: String = "editMessageCaption"
  override def parameters: ApiParameters = JsonBody(payload)

  /** Required if inline_message_id is not specified. Unique identifier for the target chat or username of the target
    * channel (in the format @channelusername)
    */
  def withChatId(chatId: String): EditMessageCaption = copy(payload.copy(chatId = Some(chatId)))

  /** Required if inline_message_id is not specified. Identifier of the message to edit
    */
  def withMessageId(messageId: Long): EditMessageCaption = copy(payload.copy(messageId = Some(messageId)))

  /** Required if chat_id and message_id are not specified. Identifier of the inline message
    */
  def withInlineMessageId(inlineMessageId: String): EditMessageCaption = copy(
    payload.copy(inlineMessageId = Some(inlineMessageId))
  )

  /** New caption of the message, 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): EditMessageCaption = copy(payload.copy(caption = Some(caption)))

  /** Mode for parsing entities in the message caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): EditMessageCaption = copy(payload.copy(parseMode = Some(parseMode)))

  /** A JSON-serialized list of special entities that appear in the caption, which can be specified instead of
    * parse_mode
    */
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): EditMessageCaption = copy(
    payload.copy(captionEntities = Some(captionEntities))
  )

  /** A JSON-serialized object for an [[https://core.telegram.org/bots/features#inline-keyboards inline keyboard]].
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): EditMessageCaption = copy(
    payload.copy(replyMarkup = Some(replyMarkup))
  )
}
