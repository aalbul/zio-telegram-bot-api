package io.github.aalbul.zio.telegram.domain

object InlineQueryResultCachedAudio {

  /** Constructs minimal [[InlineQueryResultCachedAudio]]
    * @param id
    *   Unique identifier for this result, 1-64 bytes
    * @param audioFileId
    *   A valid file identifier for the audio file
    * @return
    *   [[InlineQueryResultCachedAudio]] builder
    */
  def of(id: String, audioFileId: String): InlineQueryResultCachedAudio = InlineQueryResultCachedAudio(
    id = id,
    audioFileId = audioFileId,
    caption = None,
    parseMode = None,
    captionEntities = None,
    replyMarkup = None,
    inputMessageContent = None
  )
}

/** Represents a link to an MP3 audio file stored on the Telegram servers. By default, this audio file will be sent by
  * the user. Alternatively, you can use input_message_content to send a message with the specified content instead of
  * the audio.
  *
  * Note: This will only work in Telegram versions released after 9 April, 2016. Older clients will ignore them.
  */
case class InlineQueryResultCachedAudio(
  id: String,
  audioFileId: String,
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  replyMarkup: Option[InlineKeyboardMarkup],
  inputMessageContent: Option[InputMessageContent],
  override val `type`: String = "audio"
) extends InlineQueryResult {

  /** Caption, 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): InlineQueryResultCachedAudio = copy(caption = Some(caption))

  /** Mode for parsing entities in the audio caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): InlineQueryResultCachedAudio = copy(parseMode = Some(parseMode))

  /** List of special entities that appear in the caption, which can be specified instead of parse_mode
    */
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): InlineQueryResultCachedAudio =
    copy(captionEntities = Some(captionEntities))

  /** [[https://core.telegram.org/bots/features#inline-keyboards Inline keyboard]] attached to the message
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): InlineQueryResultCachedAudio =
    copy(replyMarkup = Some(replyMarkup))

  /** Content of the message to be sent instead of the audio
    */
  def withInputMessageContent(inputMessageContent: InputMessageContent): InlineQueryResultCachedAudio =
    copy(inputMessageContent = Some(inputMessageContent))
}
