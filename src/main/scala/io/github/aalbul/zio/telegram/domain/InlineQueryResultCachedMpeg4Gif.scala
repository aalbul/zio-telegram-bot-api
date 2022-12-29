package io.github.aalbul.zio.telegram.domain

object InlineQueryResultCachedMpeg4Gif {

  /** Constructs minimal [[InlineQueryResultCachedMpeg4Gif]]
    * @param id
    *   Unique identifier for this result, 1-64 bytes
    * @param mpeg4FileId
    *   A valid file identifier for the MPEG4 file
    * @return
    *   [[InlineQueryResultCachedMpeg4Gif]] builder
    */
  def of(id: String, mpeg4FileId: String): InlineQueryResultCachedMpeg4Gif = InlineQueryResultCachedMpeg4Gif(
    id = id,
    mpeg4FileId = mpeg4FileId,
    title = None,
    caption = None,
    parseMode = None,
    captionEntities = None,
    replyMarkup = None,
    inputMessageContent = None
  )
}

/** Represents a link to a video animation (H.264/MPEG-4 AVC video without sound) stored on the Telegram servers. By
  * default, this animated MPEG-4 file will be sent by the user with an optional caption. Alternatively, you can use
  * input_message_content to send a message with the specified content instead of the animation.
  */
case class InlineQueryResultCachedMpeg4Gif(
  id: String,
  mpeg4FileId: String,
  title: Option[String],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  replyMarkup: Option[InlineKeyboardMarkup],
  inputMessageContent: Option[InputMessageContent],
  override val `type`: String = "mpeg4_gif"
) extends InlineQueryResult {

  /** Title for the result
    */
  def withTitle(title: String): InlineQueryResultCachedMpeg4Gif = copy(title = Some(title))

  /** Caption of the MPEG-4 file to be sent, 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): InlineQueryResultCachedMpeg4Gif = copy(caption = Some(caption))

  /** Mode for parsing entities in the document caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): InlineQueryResultCachedMpeg4Gif = copy(parseMode = Some(parseMode))

  /** List of special entities that appear in the caption, which can be specified instead of parse_mode
    */
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): InlineQueryResultCachedMpeg4Gif =
    copy(captionEntities = Some(captionEntities))

  /** [[https://core.telegram.org/bots/features#inline-keyboards Inline keyboard]] attached to the message
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): InlineQueryResultCachedMpeg4Gif =
    copy(replyMarkup = Some(replyMarkup))

  /** Content of the message to be sent instead of the video animation
    */
  def withInputMessageContent(inputMessageContent: InputMessageContent): InlineQueryResultCachedMpeg4Gif =
    copy(inputMessageContent = Some(inputMessageContent))
}
