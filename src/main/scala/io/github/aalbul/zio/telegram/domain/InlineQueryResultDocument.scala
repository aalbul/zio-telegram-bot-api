package io.github.aalbul.zio.telegram.domain

object InlineQueryResultDocument {

  /** Constructs minimal [[InlineQueryResultDocument]]
    * @param id
    *   Unique identifier for this result, 1-64 bytes
    * @param title
    *   Title for the result
    * @param documentUrl
    *   A valid URL for the file
    * @param mimeType
    *   MIME type of the content of the file, either “application/pdf” or “application/zip”
    * @return
    *   [[InlineQueryResultDocument]] builder
    */
  def of(id: String, title: String, documentUrl: String, mimeType: String): InlineQueryResultDocument =
    InlineQueryResultDocument(
      id = id,
      title = title,
      caption = None,
      parseMode = None,
      captionEntities = None,
      documentUrl = documentUrl,
      mimeType = mimeType,
      description = None,
      replyMarkup = None,
      inputMessageContent = None,
      thumbUrl = None,
      thumbWidth = None,
      thumbHeight = None
    )
}

/** Represents a link to a file. By default, this file will be sent by the user with an optional caption. Alternatively,
  * you can use input_message_content to send a message with the specified content instead of the file. Currently, only
  * .PDF and .ZIP files can be sent using this method.
  *
  * Note: This will only work in Telegram versions released after 9 April, 2016. Older clients will ignore them.
  */
case class InlineQueryResultDocument(
  id: String,
  title: String,
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  documentUrl: String,
  mimeType: String,
  description: Option[String],
  replyMarkup: Option[InlineKeyboardMarkup],
  inputMessageContent: Option[InputMessageContent],
  thumbUrl: Option[String],
  thumbWidth: Option[Long],
  thumbHeight: Option[Long],
  override val `type`: String = "document"
) extends InlineQueryResult {

  /** Caption of the document to be sent, 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): InlineQueryResultDocument = copy(caption = Some(caption))

  /** Mode for parsing entities in the new caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): InlineQueryResultDocument = copy(parseMode = Some(parseMode))

  /** List of special entities that appear in the caption, which can be specified instead of parse_mode
    */
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): InlineQueryResultDocument =
    copy(captionEntities = Some(captionEntities))

  /** Short description of the result
    */
  def withDescription(description: String): InlineQueryResultDocument = copy(description = Some(description))

  /** [[https://core.telegram.org/bots/features#inline-keyboards Inline keyboard]] attached to the message
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): InlineQueryResultDocument =
    copy(replyMarkup = Some(replyMarkup))

  /** Content of the message to be sent instead of the file
    */
  def withInputMessageContent(inputMessageContent: InputMessageContent): InlineQueryResultDocument =
    copy(inputMessageContent = Some(inputMessageContent))

  /** Url of the thumbnail for the result
    */
  def withThumbUrl(thumbUrl: String): InlineQueryResultDocument = copy(thumbUrl = Some(thumbUrl))

  /** Thumbnail width
    */
  def withThumbWidth(thumbWidth: Long): InlineQueryResultDocument = copy(thumbWidth = Some(thumbWidth))

  /** Thumbnail height
    */
  def withThumbHeight(thumbHeight: Long): InlineQueryResultDocument = copy(thumbHeight = Some(thumbHeight))
}
