package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.SendInvoice.SendInvoicePayload
import io.github.aalbul.zio.telegram.domain.{InlineKeyboardMarkup, LabeledPrice, Message}

object SendInvoice {
  object SendInvoicePayload {
    implicit val sendInvoicePayloadJsonCodec: JsonValueCodec[SendInvoicePayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class SendInvoicePayload(
    chatId: String,
    messageThreadId: Option[Long],
    title: String,
    description: String,
    payload: String,
    providerToken: String,
    currency: String,
    prices: Seq[LabeledPrice],
    maxTipAmount: Option[Long],
    suggestedTipAmounts: Option[Seq[Long]],
    startParameter: Option[String],
    providerData: Option[String],
    photoUrl: Option[String],
    photoSize: Option[Long],
    photoWidth: Option[Long],
    photoHeight: Option[Long],
    needName: Option[Boolean],
    needPhoneNumber: Option[Boolean],
    needEmail: Option[Boolean],
    needShippingAddress: Option[Boolean],
    sendPhoneNumberToProvider: Option[Boolean],
    sendEmailToProvider: Option[Boolean],
    isFlexible: Option[Boolean],
    disableNotification: Option[Boolean],
    protectContent: Option[Boolean],
    replyToMessageId: Option[Long],
    allowSendingWithoutReply: Option[Boolean],
    replyMarkup: Option[InlineKeyboardMarkup]
  )

  /** Constructs minimal [[SendInvoice]] command
    * @param chatId
    *   Unique identifier for the target chat or username of the target channel (in the format @channelusername)
    * @param title
    *   Product name, 1-32 characters
    * @param description
    *   Product description, 1-255 characters
    * @param payload
    *   Bot-defined invoice payload, 1-128 bytes. This will not be displayed to the user, use for your internal
    *   processes.
    * @param providerToken
    *   Payment provider token, obtained via [[https://t.me/botfather @BotFather]]
    * @param currency
    *   Three-letter ISO 4217 currency code, see
    *   [[https://core.telegram.org/bots/payments#supported-currencies more on currencies]]
    * @param prices
    *   Price breakdown, a JSON-serialized list of components (e.g. product price, tax, discount, delivery cost,
    *   delivery tax, bonus, etc.)
    * @return
    *   [[SendInvoice]] builder
    */
  def of(
    chatId: String,
    title: String,
    description: String,
    payload: String,
    providerToken: String,
    currency: String,
    prices: Seq[LabeledPrice]
  ): SendInvoice = SendInvoice(
    SendInvoicePayload(
      chatId = chatId,
      messageThreadId = None,
      title = title,
      description = description,
      payload = payload,
      providerToken = providerToken,
      currency = currency,
      prices = prices,
      maxTipAmount = None,
      suggestedTipAmounts = None,
      startParameter = None,
      providerData = None,
      photoUrl = None,
      photoSize = None,
      photoWidth = None,
      photoHeight = None,
      needName = None,
      needPhoneNumber = None,
      needEmail = None,
      needShippingAddress = None,
      sendPhoneNumberToProvider = None,
      sendEmailToProvider = None,
      isFlexible = None,
      disableNotification = None,
      protectContent = None,
      replyToMessageId = None,
      allowSendingWithoutReply = None,
      replyMarkup = None
    )
  )
}

/** Use this method to send invoices. On success, the sent [[https://core.telegram.org/bots/api#message Message]] is
  * returned.
  */
case class SendInvoice(payload: SendInvoicePayload) extends Command[Message] {
  override val name: String = "sendInvoice"
  override def parameters: ApiParameters = JsonBody(payload)

  /** Unique identifier for the target message thread (topic) of the forum; for forum supergroups only
    */
  def withMessageThreadId(messageThreadId: Long): SendInvoice = copy(
    payload.copy(messageThreadId = Some(messageThreadId))
  )

  /** The maximum accepted amount for tips in the smallest units of the currency (integer, not float/double). For
    * example, for a maximum tip of `US$ 1.45` pass `max_tip_amount = 145`. See the exp parameter in
    * [[https://core.telegram.org/bots/payments/currencies.json currencies.json]], it shows the number of digits past
    * the decimal point for each currency (2 for the majority of currencies). Defaults to 0
    */
  def withMaxTipAmount(maxTipAmount: Long): SendInvoice = copy(payload.copy(maxTipAmount = Some(maxTipAmount)))

  /** A JSON-serialized array of suggested amounts of tips in the smallest units of the currency (integer, not
    * float/double). At most 4 suggested tip amounts can be specified. The suggested tip amounts must be positive,
    * passed in a strictly increased order and must not exceed max_tip_amount.
    */
  def withSuggestedTipAmounts(suggestedTipAmounts: Seq[Long]): SendInvoice = copy(
    payload.copy(suggestedTipAmounts = Some(suggestedTipAmounts))
  )

  /** Unique deep-linking parameter. If left empty, forwarded copies of the sent message will have a Pay button,
    * allowing multiple users to pay directly from the forwarded message, using the same invoice. If non-empty,
    * forwarded copies of the sent message will have a URL button with a deep link to the bot (instead of a Pay button),
    * with the value used as the start parameter
    */
  def withStartParameter(startParameter: String): SendInvoice = copy(
    payload.copy(startParameter = Some(startParameter))
  )

  /** JSON-serialized data about the invoice, which will be shared with the payment provider. A detailed description of
    * required fields should be provided by the payment provider.
    */
  def withProviderData(providerData: String): SendInvoice = copy(payload.copy(providerData = Some(providerData)))

  /** URL of the product photo for the invoice. Can be a photo of the goods or a marketing image for a service. People
    * like it better when they see what they are paying for.
    */
  def withPhotoUrl(photoUrl: String): SendInvoice = copy(payload.copy(photoUrl = Some(photoUrl)))

  /** Photo size in bytes
    */
  def withPhotoSize(photoSize: Long): SendInvoice = copy(payload.copy(photoSize = Some(photoSize)))

  /** Photo width
    */
  def withPhotoWidth(photoWidth: Long): SendInvoice = copy(payload.copy(photoWidth = Some(photoWidth)))

  /** Photo height
    */
  def withPhotoHeight(photoHeight: Long): SendInvoice = copy(payload.copy(photoHeight = Some(photoHeight)))

  /** Pass True if you require the user's full name to complete the order
    */
  def withNeedName(needName: Boolean): SendInvoice = copy(payload.copy(needName = Some(needName)))

  /** Pass True if you require the user's phone number to complete the order
    */
  def withNeedPhoneNumber(needPhoneNumber: Boolean): SendInvoice = copy(
    payload.copy(needPhoneNumber = Some(needPhoneNumber))
  )

  /** Pass True if you require the user's email address to complete the order
    */
  def withNeedEmail(needEmail: Boolean): SendInvoice = copy(payload.copy(needEmail = Some(needEmail)))

  /** Pass True if you require the user's shipping address to complete the order
    */
  def withNeedShippingAddress(needShippingAddress: Boolean): SendInvoice = copy(
    payload.copy(needShippingAddress = Some(needShippingAddress))
  )

  /** Pass True if the user's phone number should be sent to provider
    */
  def withSendPhoneNumberToProvider(sendPhoneNumberToProvider: Boolean): SendInvoice = copy(
    payload.copy(sendPhoneNumberToProvider = Some(sendPhoneNumberToProvider))
  )

  /** Pass True if the user's email address should be sent to provider
    */
  def withSendEmailToProvider(sendEmailToProvider: Boolean): SendInvoice = copy(
    payload.copy(sendEmailToProvider = Some(sendEmailToProvider))
  )

  /** Pass True if the final price depends on the shipping method
    */
  def withIsFlexible(isFlexible: Boolean): SendInvoice = copy(payload.copy(isFlexible = Some(isFlexible)))

  /** Sends the message [[https://telegram.org/blog/channels-2-0#silent-messages silently]]. Users will receive a
    * notification with no sound.
    */
  def withDisableNotification(disableNotification: Boolean): SendInvoice = copy(
    payload.copy(disableNotification = Some(disableNotification))
  )

  /** Protects the contents of the sent message from forwarding and saving
    */
  def withProtectContent(protectContent: Boolean): SendInvoice = copy(
    payload.copy(protectContent = Some(protectContent))
  )

  /** If the message is a reply, ID of the original message
    */
  def withReplyToMessageId(replyToMessageId: Long): SendInvoice = copy(
    payload.copy(replyToMessageId = Some(replyToMessageId))
  )

  /** Pass True if the message should be sent even if the specified replied-to message is not found
    */
  def withAllowSendingWithoutReply(allowSendingWithoutReply: Boolean): SendInvoice = copy(
    payload.copy(allowSendingWithoutReply = Some(allowSendingWithoutReply))
  )

  /** A JSON-serialized object for an [[https://core.telegram.org/bots/features#inline-keyboards inline keyboard]]. If
    * empty, one 'Pay `total price`' button will be shown. If not empty, the first button must be a Pay button.
    */
  def withReplyMarkup(replyMarkup: InlineKeyboardMarkup): SendInvoice = copy(
    payload.copy(replyMarkup = Some(replyMarkup))
  )
}
