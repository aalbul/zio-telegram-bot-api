package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.serialization.DefaultJsonCodecs

import scala.concurrent.duration.Duration

object InlineQueryResultAudio extends DefaultJsonCodecs {

  /** Constructs minimal [[InlineQueryResultAudio]]
    * @param id
    *   Unique identifier for this result, 1-64 bytes
    * @param audioUrl
    *   A valid URL for the audio file
    * @param title
    *   Title
    * @return
    *   [[InlineQueryResultAudio]] builder
    */
  def of(id: String, audioUrl: String, title: String): InlineQueryResultAudio = InlineQueryResultAudio(
    id = id,
    audioUrl = audioUrl,
    title = title,
    caption = None,
    parseMode = None,
    captionEntities = None,
    performer = None,
    audioDuration = None,
    replyMarkup = None,
    inputMessageContent = None
  )
}

/** Represents a link to an MP3 audio file. By default, this audio file will be sent by the user. Alternatively, you can
  * use input_message_content to send a message with the specified content instead of the audio.
  */
case class InlineQueryResultAudio(
  id: String,
  audioUrl: String,
  title: String,
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  performer: Option[String],
  audioDuration: Option[Duration],
  replyMarkup: Option[InlineKeyboardMarkup],
  inputMessageContent: Option[InputMessageContent],
  override val `type`: String = "audio"
) extends InlineQueryResult {

  /** Caption of the photo to be sent, 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): InlineQueryResultAudio = copy(caption = Some(caption))

  /** Mode for parsing entities in the document caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): InlineQueryResultAudio = copy(parseMode = Some(parseMode))

  /** List of special entities that appear in the caption, which can be specified instead of parse_mode
    */
  def withCaptionEntities(captionEntities: Seq[MessageEntity]): InlineQueryResultAudio =
    copy(captionEntities = Some(captionEntities))

  /** Performer
    */
  def withPerformer(performer: String): InlineQueryResultAudio = copy(performer = Some(performer))

  /** Audio duration in seconds
    */
  def withAudioDuration(audioDuration: Duration): InlineQueryResultAudio = copy(audioDuration = Some(audioDuration))

  /** [[https://core.telegram.org/bots/features#inline-keyboards Inline keyboard]] attached to the message
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): InlineQueryResultAudio =
    copy(replyMarkup = Some(replyMarkup))

  /** Content of the message to be sent instead of the audio
    */
  def withInputMessageContent(inputMessageContent: InputMessageContent): InlineQueryResultAudio =
    copy(inputMessageContent = Some(inputMessageContent))
}
