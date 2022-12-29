package io.github.aalbul.zio.telegram.domain

object InlineQueryResultCachedPhoto {

  /** Constructs minimal [[InlineQueryResultCachedPhoto]]
    * @param id
    *   Unique identifier for this result, 1-64 bytes
    * @param photoFileId
    *   A valid file identifier of the photo
    * @return
    *   [[InlineQueryResultCachedPhoto]] builder
    */
  def of(id: String, photoFileId: String): InlineQueryResultCachedPhoto = InlineQueryResultCachedPhoto(
    id = id,
    photoFileId = photoFileId,
    title = None,
    description = None,
    caption = None,
    parseMode = None,
    captionEntities = None,
    replyMarkup = None,
    inputMessageContent = None
  )
}

/** Represents a link to a photo stored on the Telegram servers. By default, this photo will be sent by the user with an
  * optional caption. Alternatively, you can use input_message_content to send a message with the specified content
  * instead of the photo.
  */
case class InlineQueryResultCachedPhoto(
  id: String,
  photoFileId: String,
  title: Option[String],
  description: Option[String],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  replyMarkup: Option[InlineKeyboardMarkup],
  inputMessageContent: Option[InputMessageContent],
  override val `type`: String = "photo"
) extends InlineQueryResult {

  /** Title for the result
    */
  def withTitle(title: String): InlineQueryResultCachedPhoto = copy(title = Some(title))

  /** Short description of the result
    */
  def withDescription(description: String): InlineQueryResultCachedPhoto = copy(description = Some(description))

  /** Caption of the photo to be sent, 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): InlineQueryResultCachedPhoto = copy(caption = Some(caption))

  /** Mode for parsing entities in the document caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): InlineQueryResultCachedPhoto = copy(parseMode = Some(parseMode))

  /** List of special entities that appear in the caption, which can be specified instead of parse_mode
    */
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): InlineQueryResultCachedPhoto =
    copy(captionEntities = Some(captionEntities))

  /** [[https://core.telegram.org/bots/features#inline-keyboards Inline keyboard]] attached to the message
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): InlineQueryResultCachedPhoto =
    copy(replyMarkup = Some(replyMarkup))

  /** Content of the message to be sent instead of the photo
    */
  def withInputMessageContent(inputMessageContent: InputMessageContent): InlineQueryResultCachedPhoto =
    copy(inputMessageContent = Some(inputMessageContent))
}
