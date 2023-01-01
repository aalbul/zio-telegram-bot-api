package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.EditMessageMedia.EditMessageMediaPayload
import io.github.aalbul.zio.telegram.domain.{InlineKeyboardMarkup, InputMedia, MessageOrInlineMessageUpdateResult}

object EditMessageMedia {
  object EditMessageMediaPayload {
    implicit val editMessageMediaPayloadJsonCodec: JsonValueCodec[EditMessageMediaPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class EditMessageMediaPayload(
    chatId: Option[String],
    messageId: Option[Long],
    inlineMessageId: Option[String],
    media: InputMedia,
    replyMarkup: Option[InlineKeyboardMarkup]
  )

  /** Constructs minimal [[EditMessageMedia]] command
    * @param media
    *   A JSON-serialized object for a new media content of the message
    * @return
    *   [[EditMessageMedia]] builder
    */
  def of(media: InputMedia): EditMessageMedia = EditMessageMedia(
    EditMessageMediaPayload(
      chatId = None,
      messageId = None,
      inlineMessageId = None,
      media = media,
      replyMarkup = None
    )
  )
}

/** Use this method to edit animation, audio, document, photo, or video messages. If a message is part of a message
  * album, then it can be edited only to an audio for audio albums, only to a document for document albums and to a
  * photo or a video otherwise. When an inline message is edited, a new file can't be uploaded; use a previously
  * uploaded file via its file_id or specify a URL. On success, if the edited message is not an inline message, the
  * edited [[https://core.telegram.org/bots/api#message Message]] is returned, otherwise True is returned.
  */
case class EditMessageMedia(payload: EditMessageMediaPayload) extends Command[MessageOrInlineMessageUpdateResult] {
  override val name: String = "editMessageMedia"
  override def parameters: ApiParameters = JsonBody(payload)

  /** Required if inline_message_id is not specified. Unique identifier for the target chat or username of the target
    * channel (in the format @channelusername)
    */
  def withChatId(chatId: String): EditMessageMedia = copy(payload.copy(chatId = Some(chatId)))

  /** Required if inline_message_id is not specified. Identifier of the message to edit
    */
  def withMessageId(messageId: Long): EditMessageMedia = copy(payload.copy(messageId = Some(messageId)))

  /** Required if chat_id and message_id are not specified. Identifier of the inline message
    */
  def withInlineMessageId(inlineMessageId: String): EditMessageMedia = copy(
    payload.copy(inlineMessageId = Some(inlineMessageId))
  )

  /** A JSON-serialized object for a new [[https://core.telegram.org/bots/features#inline-keyboards inline keyboard]].
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): EditMessageMedia = copy(
    payload.copy(replyMarkup = Some(replyMarkup))
  )
}
