package io.github.aalbul.zio.telegram.domain

import scala.concurrent.duration.Duration

object InlineQueryResultVoice {

  /** Constructs minimal [[InlineQueryResultVoice]]
    * @param id
    *   Unique identifier for this result, 1-64 bytes
    * @param voiceUrl
    *   A valid URL for the voice recording
    * @param title
    *   Recording title
    * @return
    *   [[InlineQueryResultVoice]] builder
    */
  def of(id: String, voiceUrl: String, title: String): InlineQueryResultVoice = InlineQueryResultVoice(
    id = id,
    voiceUrl = voiceUrl,
    title = title,
    caption = None,
    parseMode = None,
    captionEntities = None,
    voiceDuration = None,
    replyMarkup = None,
    inputMessageContent = None
  )
}

/** Represents a link to a voice recording in an .OGG container encoded with OPUS. By default, this voice recording will
  * be sent by the user. Alternatively, you can use input_message_content to send a message with the specified content
  * instead of the the voice message.
  *
  * Note: This will only work in Telegram versions released after 9 April, 2016. Older clients will ignore them.
  */
case class InlineQueryResultVoice(
  id: String,
  voiceUrl: String,
  title: String,
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  voiceDuration: Option[Duration],
  replyMarkup: Option[InlineKeyboardMarkup],
  inputMessageContent: Option[InputMessageContent],
  override val `type`: String = "voice"
) extends InlineQueryResult {

  /** Caption, 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): InlineQueryResultVoice = copy(caption = Some(caption))

  /** Mode for parsing entities in the voice message caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): InlineQueryResultVoice = copy(parseMode = Some(parseMode))

  /** List of special entities that appear in the caption, which can be specified instead of parse_mode
    */
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): InlineQueryResultVoice =
    copy(captionEntities = Some(captionEntities))

  /** Recording duration in seconds
    */
  def withVoiceDuration(voiceDuration: Duration): InlineQueryResultVoice = copy(voiceDuration = Some(voiceDuration))

  /** [[https://core.telegram.org/bots/features#inline-keyboards Inline keyboard]] attached to the message
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): InlineQueryResultVoice =
    copy(replyMarkup = Some(replyMarkup))

  /** Content of the message to be sent instead of the voice recording
    */
  def withInputMessageContent(inputMessageContent: InputMessageContent): InlineQueryResultVoice =
    copy(inputMessageContent = Some(inputMessageContent))
}
