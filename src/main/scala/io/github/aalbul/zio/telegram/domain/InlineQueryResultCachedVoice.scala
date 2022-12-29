package io.github.aalbul.zio.telegram.domain

object InlineQueryResultCachedVoice {

  /** Constructs minimal [[InlineQueryResultCachedVoice]]
    * @param id
    *   Unique identifier for this result, 1-64 bytes
    * @param voiceFileId
    *   A valid file identifier for the voice message
    * @param title
    *   Voice message title
    * @return
    *   [[InlineQueryResultCachedVoice]] builder
    */
  def of(id: String, voiceFileId: String, title: String): InlineQueryResultCachedVoice = InlineQueryResultCachedVoice(
    id = id,
    voiceFileId = voiceFileId,
    title = title,
    caption = None,
    parseMode = None,
    captionEntities = None,
    replyMarkup = None,
    inputMessageContent = None
  )
}

/** Represents a link to a voice message stored on the Telegram servers. By default, this voice message will be sent by
  * the user. Alternatively, you can use input_message_content to send a message with the specified content instead of
  * the voice message.
  */
case class InlineQueryResultCachedVoice(
  id: String,
  voiceFileId: String,
  title: String,
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  replyMarkup: Option[InlineKeyboardMarkup],
  inputMessageContent: Option[InputMessageContent],
  override val `type`: String = "voice"
) extends InlineQueryResult {

  /** Caption of the photo to be sent, 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): InlineQueryResultCachedVoice = copy(caption = Some(caption))

  /** Mode for parsing entities in the document caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): InlineQueryResultCachedVoice = copy(parseMode = Some(parseMode))

  /** List of special entities that appear in the caption, which can be specified instead of parse_mode
    */
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): InlineQueryResultCachedVoice =
    copy(captionEntities = Some(captionEntities))

  /** [[https://core.telegram.org/bots/features#inline-keyboards Inline keyboard]] attached to the message
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): InlineQueryResultCachedVoice =
    copy(replyMarkup = Some(replyMarkup))

  /** Content of the message to be sent instead of the voice message
    */
  def withInputMessageContent(inputMessageContent: InputMessageContent): InlineQueryResultCachedVoice =
    copy(inputMessageContent = Some(inputMessageContent))
}
