package io.github.aalbul.zio.telegram.domain

object InlineQueryResultCachedVideo {

  /** Constructs minimal [[InlineQueryResultCachedVideo]]
    * @param id
    *   Unique identifier for this result, 1-64 bytes
    * @param videoFileId
    *   A valid file identifier for the video file
    * @param title
    *   Title for the result
    * @return
    *   [[InlineQueryResultCachedVideo]] builder
    */
  def of(id: String, videoFileId: String, title: String): InlineQueryResultCachedVideo = InlineQueryResultCachedVideo(
    id = id,
    videoFileId = videoFileId,
    title = title,
    description = None,
    caption = None,
    parseMode = None,
    captionEntities = None,
    replyMarkup = None,
    inputMessageContent = None
  )
}

/** Represents a link to a video file stored on the Telegram servers. By default, this video file will be sent by the
  * user with an optional caption. Alternatively, you can use input_message_content to send a message with the specified
  * content instead of the video.
  */
case class InlineQueryResultCachedVideo(
  id: String,
  videoFileId: String,
  title: String,
  description: Option[String],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  replyMarkup: Option[InlineKeyboardMarkup],
  inputMessageContent: Option[InputMessageContent],
  override val `type`: String = "video"
) extends InlineQueryResult {

  /** Short description of the result
    */
  def withDescription(description: String): InlineQueryResultCachedVideo = copy(description = Some(description))

  /** Caption of the photo to be sent, 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): InlineQueryResultCachedVideo = copy(caption = Some(caption))

  /** Mode for parsing entities in the document caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): InlineQueryResultCachedVideo = copy(parseMode = Some(parseMode))

  /** List of special entities that appear in the caption, which can be specified instead of parse_mode
    */
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): InlineQueryResultCachedVideo =
    copy(captionEntities = Some(captionEntities))

  /** [[https://core.telegram.org/bots/features#inline-keyboards Inline keyboard]] attached to the message
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): InlineQueryResultCachedVideo =
    copy(replyMarkup = Some(replyMarkup))

  /** Content of the message to be sent instead of the video
    */
  def withInputMessageContent(inputMessageContent: InputMessageContent): InlineQueryResultCachedVideo =
    copy(inputMessageContent = Some(inputMessageContent))
}
