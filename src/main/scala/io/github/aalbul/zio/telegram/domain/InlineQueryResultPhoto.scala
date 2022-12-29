package io.github.aalbul.zio.telegram.domain

object InlineQueryResultPhoto {

  /** Constructs minimal [[InlineQueryResultPhoto]]
    * @param id
    *   Unique identifier for this result, 1-64 bytes
    * @param photoUrl
    *   A valid URL of the photo. Photo must be in JPEG format. Photo size must not exceed 5MB
    * @param thumbUrl
    *   URL of the thumbnail for the photo
    * @return
    *   [[InlineQueryResultPhoto]] builder
    */
  def of(id: String, photoUrl: String, thumbUrl: String): InlineQueryResultPhoto = InlineQueryResultPhoto(
    id = id,
    photoUrl = photoUrl,
    thumbUrl = thumbUrl,
    photoWidth = None,
    photoHeight = None,
    title = None,
    description = None,
    caption = None,
    parseMode = None,
    captionEntities = None,
    replyMarkup = None,
    inputMessageContent = None
  )
}

case class InlineQueryResultPhoto(
  id: String,
  photoUrl: String,
  thumbUrl: String,
  photoWidth: Option[Long],
  photoHeight: Option[Long],
  title: Option[String],
  description: Option[String],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  replyMarkup: Option[InlineKeyboardMarkup],
  inputMessageContent: Option[InputMessageContent],
  override val `type`: String = "photo"
) extends InlineQueryResult {

  /** Width of the photo
    */
  def withPhotoWidth(photoWidth: Long): InlineQueryResultPhoto = copy(photoWidth = Some(photoWidth))

  /** Height of the photo
    */
  def withPhotoHeight(photoHeight: Long): InlineQueryResultPhoto = copy(photoHeight = Some(photoHeight))

  /** Title for the result
    */
  def withTitle(title: String): InlineQueryResultPhoto = copy(title = Some(title))

  /** Short description of the result
    */
  def withDescription(description: String): InlineQueryResultPhoto = copy(description = Some(description))

  /** Caption of the photo to be sent, 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): InlineQueryResultPhoto = copy(caption = Some(caption))

  /** Mode for parsing entities in the document caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): InlineQueryResultPhoto = copy(parseMode = Some(parseMode))

  /** List of special entities that appear in the caption, which can be specified instead of parse_mode
    */
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): InlineQueryResultPhoto =
    copy(captionEntities = Some(captionEntities))

  /** [[https://core.telegram.org/bots/features#inline-keyboards Inline keyboard]] attached to the message
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): InlineQueryResultPhoto =
    copy(replyMarkup = Some(replyMarkup))

  /** Content of the message to be sent instead of the photo
    */
  def withInputMessageContent(inputMessageContent: InputMessageContent): InlineQueryResultPhoto =
    copy(inputMessageContent = Some(inputMessageContent))
}
