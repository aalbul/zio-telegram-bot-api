package io.github.aalbul.zio.telegram.domain

import scala.concurrent.duration.Duration

object InlineQueryResultMpeg4Gif {

  /** Constructs minimal [[InlineQueryResultMpeg4Gif]]
    * @param id
    *   Unique identifier for this result, 1-64 bytes
    * @param mpeg4Url
    *   A valid URL for the MPEG4 file. File size must not exceed 1MB
    * @param thumbUrl
    *   URL of the static (JPEG or GIF) or animated (MPEG4) thumbnail for the result
    * @return
    *   [[InlineQueryResultMpeg4Gif]] builder
    */
  def of(id: String, mpeg4Url: String, thumbUrl: String): InlineQueryResultMpeg4Gif = InlineQueryResultMpeg4Gif(
    id = id,
    mpeg4Url = mpeg4Url,
    mpeg4Width = None,
    mpeg4Height = None,
    mpeg4Duration = None,
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

/** Represents a link to a video animation (H.264/MPEG-4 AVC video without sound). By default, this animated MPEG-4 file
  * will be sent by the user with optional caption. Alternatively, you can use input_message_content to send a message
  * with the specified content instead of the animation.
  */
case class InlineQueryResultMpeg4Gif(
  id: String,
  mpeg4Url: String,
  mpeg4Width: Option[Long],
  mpeg4Height: Option[Long],
  mpeg4Duration: Option[Duration],
  thumbUrl: String,
  thumbMimeType: Option[String],
  title: Option[String],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  replyMarkup: Option[InlineKeyboardMarkup],
  inputMessageContent: Option[InputMessageContent],
  override val `type`: String = "mpeg4_gif"
) extends InlineQueryResult {

  /** Video width
    */
  def withMpeg4Width(mpeg4Width: Long): InlineQueryResultMpeg4Gif = copy(mpeg4Width = Some(mpeg4Width))

  /** Video height
    */
  def withMpeg4Height(mpeg4Height: Long): InlineQueryResultMpeg4Gif = copy(mpeg4Height = Some(mpeg4Height))

  /** Video duration in seconds
    */
  def withMpeg4Duration(mpeg4Duration: Duration): InlineQueryResultMpeg4Gif = copy(mpeg4Duration = Some(mpeg4Duration))

  /** MIME type of the thumbnail, must be one of “image/jpeg”, “image/gif”, or “video/mp4”. Defaults to “image/jpeg”
    */
  def withThumbMimeType(thumbMimeType: String): InlineQueryResultMpeg4Gif = copy(thumbMimeType = Some(thumbMimeType))

  /** Title for the result
    */
  def withTitle(title: String): InlineQueryResultMpeg4Gif = copy(title = Some(title))

  /** Caption of the MPEG-4 file to be sent, 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): InlineQueryResultMpeg4Gif = copy(caption = Some(caption))

  /** Mode for parsing entities in the document caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): InlineQueryResultMpeg4Gif = copy(parseMode = Some(parseMode))

  /** List of special entities that appear in the caption, which can be specified instead of parse_mode
    */
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): InlineQueryResultMpeg4Gif =
    copy(captionEntities = Some(captionEntities))

  /** [[https://core.telegram.org/bots/features#inline-keyboards Inline keyboard]] attached to the message
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): InlineQueryResultMpeg4Gif =
    copy(replyMarkup = Some(replyMarkup))

  /** Content of the message to be sent instead of the video animation
    */
  def withInputMessageContent(inputMessageContent: InputMessageContent): InlineQueryResultMpeg4Gif =
    copy(inputMessageContent = Some(inputMessageContent))
}
