package io.github.aalbul.zio.telegram.domain

object InlineQueryResultArticle {

  /** Constructs minimal [[InlineQueryResultArticle]]
    * @param id
    *   Unique identifier for this result, 1-64 Bytes
    * @param title
    *   Title of the result
    * @param inputMessageContent
    *   Content of the message to be sent
    * @return
    *   [[InlineQueryResultArticle]] builder
    */
  def of(id: String, title: String, inputMessageContent: InputMessageContent): InlineQueryResultArticle =
    InlineQueryResultArticle(
      id = id,
      title = title,
      inputMessageContent = inputMessageContent,
      replyMarkup = None,
      url = None,
      hideUrl = None,
      description = None,
      thumbUrl = None,
      thumbWidth = None,
      thumbHeight = None
    )
}

/** Represents a link to an article or web page.
  */
case class InlineQueryResultArticle(
  id: String,
  title: String,
  inputMessageContent: InputMessageContent,
  replyMarkup: Option[InlineKeyboardMarkup],
  url: Option[String],
  hideUrl: Option[Boolean],
  description: Option[String],
  thumbUrl: Option[String],
  thumbWidth: Option[Long],
  thumbHeight: Option[Long],
  override val `type`: String = "article"
) extends InlineQueryResult {

  /** [[https://core.telegram.org/bots/features#inline-keyboards Inline keyboard]] attached to the message
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): InlineQueryResultArticle =
    copy(replyMarkup = Some(replyMarkup))

  /** URL of the result
    */
  def withUrl(url: String): InlineQueryResultArticle = copy(url = Some(url))

  /** Pass True if you don't want the URL to be shown in the message
    */
  def withHideUrl(hideUrl: Boolean): InlineQueryResultArticle = copy(hideUrl = Some(hideUrl))

  /** Short description of the result
    */
  def withDescription(description: String): InlineQueryResultArticle = copy(description = Some(description))

  /** Url of the thumbnail for the result
    */
  def withThumbUrl(thumbUrl: String): InlineQueryResultArticle = copy(thumbUrl = Some(thumbUrl))

  /** Thumbnail width
    */
  def withThumbWidth(thumbWidth: Long): InlineQueryResultArticle = copy(thumbWidth = Some(thumbWidth))

  /** Thumbnail height
    */
  def withThumbHeight(thumbHeight: Long): InlineQueryResultArticle = copy(thumbHeight = Some(thumbHeight))
}
