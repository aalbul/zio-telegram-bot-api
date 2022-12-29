package io.github.aalbul.zio.telegram.domain

object InlineQueryResultCachedSticker {

  /** Constructs minimal [[InlineQueryResultCachedSticker]]
    * @param id
    *   Unique identifier for this result, 1-64 bytes
    * @param stickerFileId
    *   A valid file identifier of the sticker
    * @return
    *   [[InlineQueryResultCachedSticker]] builder
    */
  def of(id: String, stickerFileId: String): InlineQueryResultCachedSticker = InlineQueryResultCachedSticker(
    id = id,
    stickerFileId = stickerFileId,
    replyMarkup = None,
    inputMessageContent = None
  )
}

/** Represents a link to a sticker stored on the Telegram servers. By default, this sticker will be sent by the user.
  * Alternatively, you can use input_message_content to send a message with the specified content instead of the
  * sticker.
  */
case class InlineQueryResultCachedSticker(
  id: String,
  stickerFileId: String,
  replyMarkup: Option[InlineKeyboardMarkup],
  inputMessageContent: Option[InputMessageContent],
  override val `type`: String = "sticker"
) extends InlineQueryResult {

  /** [[https://core.telegram.org/bots/features#inline-keyboards Inline keyboard]] attached to the message
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): InlineQueryResultCachedSticker =
    copy(replyMarkup = Some(replyMarkup))

  /** Content of the message to be sent instead of the sticker
    */
  def withInputMessageContent(inputMessageContent: InputMessageContent): InlineQueryResultCachedSticker =
    copy(inputMessageContent = Some(inputMessageContent))
}
