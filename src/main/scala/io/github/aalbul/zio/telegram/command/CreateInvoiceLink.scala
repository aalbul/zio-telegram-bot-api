package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.CreateInvoiceLink.CreateInvoiceLinkPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.LabeledPrice

object CreateInvoiceLink {
  object CreateInvoiceLinkPayload {
    implicit val createInvoiceLinkPayloadJsonCodec: JsonValueCodec[CreateInvoiceLinkPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class CreateInvoiceLinkPayload(
    title: String,
    description: String,
    payload: String,
    providerToken: String,
    currency: String,
    prices: Seq[LabeledPrice],
    maxTipAmount: Option[Long],
    suggestedTipAmounts: Option[Seq[Long]],
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
    isFlexible: Option[Boolean]
  )

  /** Constructs minimal [[CreateInvoiceLink]] command
    * @param title
    *   Product name, 1-32 characters
    * @param description
    *   Product description, 1-255 characters
    * @param payload
    *   Bot-defined invoice payload, 1-128 bytes. This will not be displayed to the user, use for your internal
    *   processes.
    * @param providerToken
    *   Payment provider token, obtained via [[https://t.me/botfather BotFather]]
    * @param currency
    *   Three-letter ISO 4217 currency code, see
    *   [[https://core.telegram.org/bots/payments#supported-currencies more on currencies]]
    * @param prices
    *   Price breakdown, a JSON-serialized list of components (e.g. product price, tax, discount, delivery cost,
    *   delivery tax, bonus, etc.)
    * @return
    *   [[CreateInvoiceLink]] builder
    */
  def of(
    title: String,
    description: String,
    payload: String,
    providerToken: String,
    currency: String,
    prices: Seq[LabeledPrice]
  ): CreateInvoiceLink = CreateInvoiceLink(
    CreateInvoiceLinkPayload(
      title = title,
      description = description,
      payload = payload,
      providerToken = providerToken,
      currency = currency,
      prices = prices,
      maxTipAmount = None,
      suggestedTipAmounts = None,
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
      isFlexible = None
    )
  )
}

/** Use this method to create a link for an invoice. Returns the created invoice link as String on success.
  */
case class CreateInvoiceLink(payload: CreateInvoiceLinkPayload) extends Command[String] {
  override val name: String = "createInvoiceLink"
  override def parameters: ApiParameters = JsonBody(payload)

  /** The maximum accepted amount for tips in the smallest units of the currency (integer, not float/double). For
    * example, for a maximum tip of `US$ 1.45` pass `max_tip_amount = 145`. See the exp parameter in
    * [[https://core.telegram.org/bots/payments/currencies.json currencies.json]], it shows the number of digits past
    * the decimal point for each currency (2 for the majority of currencies). Defaults to 0
    */
  def withMaxTipAmount(maxTipAmount: Long): CreateInvoiceLink = copy(payload.copy(maxTipAmount = Some(maxTipAmount)))

  /** A JSON-serialized array of suggested amounts of tips in the smallest units of the currency (integer, not
    * float/double). At most 4 suggested tip amounts can be specified. The suggested tip amounts must be positive,
    * passed in a strictly increased order and must not exceed max_tip_amount.
    */
  def withSuggestedTipAmounts(suggestedTipAmounts: Seq[Long]): CreateInvoiceLink = copy(
    payload.copy(suggestedTipAmounts = Some(suggestedTipAmounts))
  )

  /** JSON-serialized data about the invoice, which will be shared with the payment provider. A detailed description of
    * required fields should be provided by the payment provider.
    */
  def withProviderData(providerData: String): CreateInvoiceLink = copy(payload.copy(providerData = Some(providerData)))

  /** URL of the product photo for the invoice. Can be a photo of the goods or a marketing image for a service.
    */
  def withPhotoUrl(photoUrl: String): CreateInvoiceLink = copy(payload.copy(photoUrl = Some(photoUrl)))

  /** Photo size in bytes
    */
  def withPhotoSize(photoSize: Long): CreateInvoiceLink = copy(payload.copy(photoSize = Some(photoSize)))

  /** Photo width
    */
  def withPhotoWidth(photoWidth: Long): CreateInvoiceLink = copy(payload.copy(photoWidth = Some(photoWidth)))

  /** Photo height
    */
  def withPhotoHeight(photoHeight: Long): CreateInvoiceLink = copy(payload.copy(photoHeight = Some(photoHeight)))

  /** Pass True if you require the user's full name to complete the order
    */
  def withNeedName(needName: Boolean): CreateInvoiceLink = copy(payload.copy(needName = Some(needName)))

  /** Pass True if you require the user's phone number to complete the order
    */
  def withNeedPhoneNumber(needPhoneNumber: Boolean): CreateInvoiceLink = copy(
    payload.copy(needPhoneNumber = Some(needPhoneNumber))
  )

  /** Pass True if you require the user's email address to complete the order
    */
  def withNeedEmail(needEmail: Boolean): CreateInvoiceLink = copy(payload.copy(needEmail = Some(needEmail)))

  /** Pass True if you require the user's shipping address to complete the order
    */
  def withNeedShippingAddress(needShippingAddress: Boolean): CreateInvoiceLink = copy(
    payload.copy(needShippingAddress = Some(needShippingAddress))
  )

  /** Pass True if the user's phone number should be sent to the provider
    */
  def withSendPhoneNumberToProvider(sendPhoneNumberToProvider: Boolean): CreateInvoiceLink = copy(
    payload.copy(sendPhoneNumberToProvider = Some(sendPhoneNumberToProvider))
  )

  /** Pass True if the user's email address should be sent to the provider
    */
  def withSendEmailToProvider(sendEmailToProvider: Boolean): CreateInvoiceLink = copy(
    payload.copy(sendEmailToProvider = Some(sendEmailToProvider))
  )

  /** Pass True if the final price depends on the shipping method
    */
  def withIsFlexible(isFlexible: Boolean): CreateInvoiceLink = copy(payload.copy(isFlexible = Some(isFlexible)))
}
