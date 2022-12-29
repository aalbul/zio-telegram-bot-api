package io.github.aalbul.zio.telegram.domain

object InlineQueryResultContact {

  /** Constructs minimal [[InlineQueryResultContact]]
    * @param id
    *   Unique identifier for this result, 1-64 Bytes
    * @param phoneNumber
    *   Contact's phone number
    * @param firstName
    *   Contact's first name
    * @return
    *   [[InlineQueryResultContact]] builder
    */
  def of(id: String, phoneNumber: String, firstName: String): InlineQueryResultContact = InlineQueryResultContact(
    id = id,
    phoneNumber = phoneNumber,
    firstName = firstName,
    lastName = None,
    vcard = None,
    replyMarkup = None,
    inputMessageContent = None,
    thumbUrl = None,
    thumbWidth = None,
    thumbHeight = None
  )
}

/** Represents a contact with a phone number. By default, this contact will be sent by the user. Alternatively, you can
  * use input_message_content to send a message with the specified content instead of the contact.
  *
  * Note: This will only work in Telegram versions released after 9 April, 2016. Older clients will ignore them.
  */
case class InlineQueryResultContact(
  id: String,
  phoneNumber: String,
  firstName: String,
  lastName: Option[String],
  vcard: Option[String],
  replyMarkup: Option[InlineKeyboardMarkup],
  inputMessageContent: Option[InputMessageContent],
  thumbUrl: Option[String],
  thumbWidth: Option[Long],
  thumbHeight: Option[Long],
  override val `type`: String = "contact"
) extends InlineQueryResult {

  /** Contact's last name
    */
  def withLastName(lastName: String): InlineQueryResultContact = copy(lastName = Some(lastName))

  /** Additional data about the contact in the form of a [[https://en.wikipedia.org/wiki/VCard vCard]], 0-2048 bytes
    */
  def withVcard(vcard: String): InlineQueryResultContact = copy(vcard = Some(vcard))

  /** [[https://core.telegram.org/bots/features#inline-keyboards Inline keyboard]] attached to the message
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): InlineQueryResultContact =
    copy(replyMarkup = Some(replyMarkup))

  /** Content of the message to be sent instead of the contact
    */
  def withInputMessageContent(inputMessageContent: InputMessageContent): InlineQueryResultContact =
    copy(inputMessageContent = Some(inputMessageContent))

  /** Url of the thumbnail for the result
    */
  def withThumbUrl(thumbUrl: String): InlineQueryResultContact = copy(thumbUrl = Some(thumbUrl))

  /** Thumbnail width
    */
  def withThumbWidth(thumbWidth: Long): InlineQueryResultContact = copy(thumbWidth = Some(thumbWidth))

  /** Thumbnail height
    */
  def withThumbHeight(thumbHeight: Long): InlineQueryResultContact = copy(thumbHeight = Some(thumbHeight))
}
