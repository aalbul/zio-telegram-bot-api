package io.github.aalbul.zio.telegram.domain

import scala.concurrent.duration.Duration

object InlineQueryResultGif {

  /** Constructs minimal [[InlineQueryResultGif]]
    * @param id
    *   Unique identifier for this result, 1-64 bytes
    * @param gifUrl
    *   A valid URL for the GIF file. File size must not exceed 1MB
    * @param thumbUrl
    *   URL of the static (JPEG or GIF) or animated (MPEG4) thumbnail for the result
    * @return
    *   [[InlineQueryResultGif]] builder
    */
  def of(id: String, gifUrl: String, thumbUrl: String): InlineQueryResultGif = InlineQueryResultGif(
    id = id,
    gifUrl = gifUrl,
    gifWidth = None,
    gifHeight = None,
    gifDuration = None,
    thumbUrl = thumbUrl,
    thumbMimeType = None,
    title = None,
    caption = None,
    parseMode = None,
    captionEntities = None,
    replyMarkup = None,
    inputMessageContent = None
  )
}

case class InlineQueryResultGif(
  id: String,
  gifUrl: String,
  gifWidth: Option[Long],
  gifHeight: Option[Long],
  gifDuration: Option[Duration],
  thumbUrl: String,
  thumbMimeType: Option[String],
  title: Option[String],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  replyMarkup: Option[InlineKeyboardMarkup],
  inputMessageContent: Option[InputMessageContent],
  override val `type`: String = "gif"
) extends InlineQueryResult {

  /** Width of the GIF
    */
  def withGifWidth(gifWidth: Long): InlineQueryResultGif = copy(gifWidth = Some(gifWidth))

  /** Height of the GIF
    */
  def withGifHeight(gifHeight: Long): InlineQueryResultGif = copy(gifHeight = Some(gifHeight))

  /** Duration of the GIF in seconds
    */
  def withGifDuration(gifDuration: Duration): InlineQueryResultGif = copy(gifDuration = Some(gifDuration))

  /** MIME type of the thumbnail, must be one of “image/jpeg”, “image/gif”, or “video/mp4”. Defaults to “image/jpeg”
    */
  def withThumbMimeType(thumbMimeType: String): InlineQueryResultGif = copy(thumbMimeType = Some(thumbMimeType))

  /** Title for the result
    */
  def withTitle(title: String): InlineQueryResultGif = copy(title = Some(title))

  /** Caption of the GIF file to be sent, 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): InlineQueryResultGif = copy(caption = Some(caption))

  /** Mode for parsing entities in the document caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): InlineQueryResultGif = copy(parseMode = Some(parseMode))

  /** List of special entities that appear in the caption, which can be specified instead of parse_mode
    */
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): InlineQueryResultGif =
    copy(captionEntities = Some(captionEntities))

  /** [[https://core.telegram.org/bots/features#inline-keyboards Inline keyboard]] attached to the message
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): InlineQueryResultGif =
    copy(replyMarkup = Some(replyMarkup))

  /** Content of the message to be sent instead of the GIF animation
    */
  def withInputMessageContent(inputMessageContent: InputMessageContent): InlineQueryResultGif =
    copy(inputMessageContent = Some(inputMessageContent))
}
