package io.github.aalbul.zio.telegram.domain

object InlineQueryResultCachedGif {

  /** Constructs minimal [[InlineQueryResultCachedGif]]
    * @param id
    *   Unique identifier for this result, 1-64 bytes
    * @param gifFileId
    *   A valid file identifier for the GIF file
    * @return
    *   [[InlineQueryResultCachedGif]] builder
    */
  def of(id: String, gifFileId: String): InlineQueryResultCachedGif = InlineQueryResultCachedGif(
    id = id,
    gifFileId = gifFileId,
    title = None,
    caption = None,
    parseMode = None,
    captionEntities = None,
    replyMarkup = None,
    inputMessageContent = None
  )
}

/** Represents a link to an animated GIF file stored on the Telegram servers. By default, this animated GIF file will be
  * sent by the user with an optional caption. Alternatively, you can use input_message_content to send a message with
  * specified content instead of the animation.
  */
case class InlineQueryResultCachedGif(
  id: String,
  gifFileId: String,
  title: Option[String],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  replyMarkup: Option[InlineKeyboardMarkup],
  inputMessageContent: Option[InputMessageContent],
  override val `type`: String = "gif"
) extends InlineQueryResult {

  /** Title for the result
    */
  def withTitle(title: String): InlineQueryResultCachedGif = copy(title = Some(title))

  /** Caption of the GIF file to be sent, 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): InlineQueryResultCachedGif = copy(caption = Some(caption))

  /** Mode for parsing entities in the document caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): InlineQueryResultCachedGif = copy(parseMode = Some(parseMode))

  /** List of special entities that appear in the caption, which can be specified instead of parse_mode
    */
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): InlineQueryResultCachedGif =
    copy(captionEntities = Some(captionEntities))

  /** [[https://core.telegram.org/bots/features#inline-keyboards Inline keyboard]] attached to the message
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): InlineQueryResultCachedGif =
    copy(replyMarkup = Some(replyMarkup))

  /** Content of the message to be sent instead of the GIF animation
    */
  def withInputMessageContent(inputMessageContent: InputMessageContent): InlineQueryResultCachedGif =
    copy(inputMessageContent = Some(inputMessageContent))
}
