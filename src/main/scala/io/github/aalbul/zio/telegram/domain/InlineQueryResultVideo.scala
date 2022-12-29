package io.github.aalbul.zio.telegram.domain

import scala.concurrent.duration.Duration

object InlineQueryResultVideo {

  /** Constructs minimal [[InlineQueryResultVideo]]
    * @param id
    *   Unique identifier for this result, 1-64 bytes
    * @param videoUrl
    *   A valid URL for the embedded video player or video file
    * @param mimeType
    *   MIME type of the content of the video URL, “text/html” or “video/mp4”
    * @param thumbUrl
    *   URL of the thumbnail (JPEG only) for the video
    * @param title
    *   Title for the result
    * @return
    *   [[InlineQueryResultVideo]] builder
    */
  def of(id: String, videoUrl: String, mimeType: String, thumbUrl: String, title: String): InlineQueryResultVideo =
    InlineQueryResultVideo(
      id = id,
      videoUrl = videoUrl,
      mimeType = mimeType,
      thumbUrl = thumbUrl,
      title = title,
      caption = None,
      parseMode = None,
      captionEntities = None,
      videoWidth = None,
      videoHeight = None,
      videoDuration = None,
      description = None,
      replyMarkup = None,
      inputMessageContent = None
    )
}

/** Represents a link to a page containing an embedded video player or a video file. By default, this video file will be
  * sent by the user with an optional caption. Alternatively, you can use input_message_content to send a message with
  * the specified content instead of the video.
  *
  * If an InlineQueryResultVideo message contains an embedded video (e.g., YouTube), you must replace its content using
  * input_message_content.
  */
case class InlineQueryResultVideo(
  id: String,
  videoUrl: String,
  mimeType: String,
  thumbUrl: String,
  title: String,
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  videoWidth: Option[Long],
  videoHeight: Option[Long],
  videoDuration: Option[Duration],
  description: Option[String],
  replyMarkup: Option[InlineKeyboardMarkup],
  inputMessageContent: Option[InputMessageContent],
  override val `type`: String = "video"
) extends InlineQueryResult {

  /** Caption of the video to be sent, 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): InlineQueryResultVideo = copy(caption = Some(caption))

  /** Mode for parsing entities in the video caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): InlineQueryResultVideo = copy(parseMode = Some(parseMode))

  /** List of special entities that appear in the caption, which can be specified instead of parse_mode
    */
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): InlineQueryResultVideo =
    copy(captionEntities = Some(captionEntities))

  /** Video width
    */
  def withVideoWidth(videoWidth: Long): InlineQueryResultVideo = copy(videoWidth = Some(videoWidth))

  /** Video height
    */
  def withVideoHeight(videoHeight: Long): InlineQueryResultVideo = copy(videoHeight = Some(videoHeight))

  /** Video duration in seconds
    */
  def withVideoDuration(videoDuration: Duration): InlineQueryResultVideo = copy(videoDuration = Some(videoDuration))

  /** Short description of the result
    */
  def withDescription(description: String): InlineQueryResultVideo = copy(description = Some(description))

  /** [[https://core.telegram.org/bots/features#inline-keyboards Inline keyboard]] attached to the message
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): InlineQueryResultVideo =
    copy(replyMarkup = Some(replyMarkup))

  /** Content of the message to be sent instead of the video. This field is required if InlineQueryResultVideo is used
    * to send an HTML-page as a result (e.g., a YouTube video).
    */
  def withInputMessageContent(inputMessageContent: InputMessageContent): InlineQueryResultVideo =
    copy(inputMessageContent = Some(inputMessageContent))
}
