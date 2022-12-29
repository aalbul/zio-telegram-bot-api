package io.github.aalbul.zio.telegram.domain

object InlineQueryResultCachedDocument {

  /** Constructs minimal [[InlineQueryResultCachedDocument]]
    * @param id
    *   Unique identifier for this result, 1-64 bytes
    * @param title
    *   Title for the result
    * @param documentFileId
    *   A valid file identifier for the file
    * @return
    *   [[InlineQueryResultCachedDocument]] builder
    */
  def of(id: String, title: String, documentFileId: String): InlineQueryResultCachedDocument =
    InlineQueryResultCachedDocument(
      id = id,
      title = title,
      documentFileId = documentFileId,
      description = None,
      caption = None,
      parseMode = None,
      captionEntities = None,
      replyMarkup = None,
      inputMessageContent = None
    )
}

/** Represents a link to a file stored on the Telegram servers. By default, this file will be sent by the user with an
  * optional caption. Alternatively, you can use input_message_content to send a message with the specified content
  * instead of the file.
  */
case class InlineQueryResultCachedDocument(
  id: String,
  title: String,
  documentFileId: String,
  description: Option[String],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  replyMarkup: Option[InlineKeyboardMarkup],
  inputMessageContent: Option[InputMessageContent],
  override val `type`: String = "document"
) extends InlineQueryResult {

  /** Short description of the result
    */
  def withDescription(description: String): InlineQueryResultCachedDocument = copy(description = Some(description))

  /** Caption of the document to be sent, 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): InlineQueryResultCachedDocument = copy(caption = Some(caption))

  /** Mode for parsing entities in the document caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): InlineQueryResultCachedDocument = copy(parseMode = Some(parseMode))

  /** List of special entities that appear in the caption, which can be specified instead of parse_mode
    */
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): InlineQueryResultCachedDocument =
    copy(captionEntities = Some(captionEntities))

  /** [[https://core.telegram.org/bots/features#inline-keyboards Inline keyboard]] attached to the message
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): InlineQueryResultCachedDocument =
    copy(replyMarkup = Some(replyMarkup))

  /** Content of the message to be sent instead of the file
    */
  def withInputMessageContent(inputMessageContent: InputMessageContent): InlineQueryResultCachedDocument =
    copy(inputMessageContent = Some(inputMessageContent))
}
