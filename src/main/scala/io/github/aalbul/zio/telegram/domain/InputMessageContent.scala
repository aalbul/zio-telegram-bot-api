package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.{JsonReader, JsonValueCodec, JsonWriter}
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.serialization.DefaultJsonCodecs

import scala.concurrent.duration.Duration

object InputMessageContent extends DefaultJsonCodecs {
  private val inputTextMessageContentJsonCodec: JsonValueCodec[InputTextMessageContent] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
  private val inputLocationMessageContentJsonCodec: JsonValueCodec[InputLocationMessageContent] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
  private val inputVenueMessageContentJsonCodec: JsonValueCodec[InputVenueMessageContent] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
  private val inputContactMessageContentJsonCodec: JsonValueCodec[InputContactMessageContent] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
  private val inputInvoiceMessageContentJsonCodec: JsonValueCodec[InputInvoiceMessageContent] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  implicit val inputMessageContentJsonCodec: JsonValueCodec[InputMessageContent] =
    new JsonValueCodec[InputMessageContent] {
      override def decodeValue(in: JsonReader, default: InputMessageContent): InputMessageContent = {
        in.setMark()
        val data = new String(in.readRawValAsBytes())
        in.rollbackToMark()

        if (data.contains("\"message_text\"")) inputTextMessageContentJsonCodec.decodeValue(in, null)
        else if (data.contains("\"address\"")) inputVenueMessageContentJsonCodec.decodeValue(in, null)
        else if (data.contains("\"latitude\"")) inputLocationMessageContentJsonCodec.decodeValue(in, null)
        else if (data.contains("\"phone_number\"")) inputContactMessageContentJsonCodec.decodeValue(in, null)
        else inputInvoiceMessageContentJsonCodec.decodeValue(in, null)
      }

      override def encodeValue(content: InputMessageContent, out: JsonWriter): Unit = content match {
        case inputText: InputTextMessageContent => inputTextMessageContentJsonCodec.encodeValue(inputText, out)
        case inputLocation: InputLocationMessageContent =>
          inputLocationMessageContentJsonCodec.encodeValue(inputLocation, out)
        case inputVenue: InputVenueMessageContent => inputVenueMessageContentJsonCodec.encodeValue(inputVenue, out)
        case inputContact: InputContactMessageContent =>
          inputContactMessageContentJsonCodec.encodeValue(inputContact, out)
        case inputInvoice: InputInvoiceMessageContent =>
          inputInvoiceMessageContentJsonCodec.encodeValue(inputInvoice, out)
      }

      override def nullValue: InputMessageContent = null
    }
}

sealed trait InputMessageContent

object InputTextMessageContent {

  /** Constructs minimal [[InputTextMessageContent]]
    * @param messageText
    *   Text of the message to be sent, 1-4096 characters
    * @return
    *   [[InputTextMessageContent]] builder
    */
  def of(messageText: String): InputTextMessageContent = InputTextMessageContent(
    messageText = messageText,
    parseMode = None,
    entities = None,
    disableWebPagePreview = None
  )
}

/** Represents the [[https://core.telegram.org/bots/api#inputmessagecontent content]] of a text message to be sent as
  * the result of an inline query.
  */
case class InputTextMessageContent(
  messageText: String,
  parseMode: Option[ParseMode],
  entities: Option[Seq[MessageEntity]],
  disableWebPagePreview: Option[Boolean]
) extends InputMessageContent {

  /** Mode for parsing entities in the message text. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): InputTextMessageContent = copy(parseMode = Some(parseMode))

  /** List of special entities that appear in message text, which can be specified instead of parse_mode
    */
  def withEntities(entities: Seq[MessageEntity]): InputTextMessageContent = copy(entities = Some(entities))

  /** Disables link previews for links in the sent message
    */
  def withDisableWebPagePreview(disableWebPagePreview: Boolean): InputTextMessageContent =
    copy(disableWebPagePreview = Some(disableWebPagePreview))
}

object InputLocationMessageContent {

  /** Constructs minimal [[InputLocationMessageContent]]
    * @param latitude
    *   Latitude of the location in degrees
    * @param longitude
    *   Longitude of the location in degrees
    * @return
    *   [[InputLocationMessageContent]] builder
    */
  def of(latitude: Double, longitude: Double): InputLocationMessageContent = InputLocationMessageContent(
    latitude = latitude,
    longitude = longitude,
    horizontalAccuracy = None,
    livePeriod = None,
    heading = None,
    proximityAlertRadius = None
  )
}

/** Represents the [[https://core.telegram.org/bots/api#inputmessagecontent content]] of a location message to be sent
  * as the result of an inline query.
  */
case class InputLocationMessageContent(
  latitude: Double,
  longitude: Double,
  horizontalAccuracy: Option[Double],
  livePeriod: Option[Duration],
  heading: Option[Int],
  proximityAlertRadius: Option[Long]
) extends InputMessageContent {

  /** The radius of uncertainty for the location, measured in meters; 0-1500
    */
  def withHorizontalAccuracy(horizontalAccuracy: Double): InputLocationMessageContent =
    copy(horizontalAccuracy = Some(horizontalAccuracy))

  /** Period in seconds for which the location can be updated, should be between 60 and 86400.
    */
  def withLivePeriod(livePeriod: Duration): InputLocationMessageContent = copy(livePeriod = Some(livePeriod))

  /** For live locations, a direction in which the user is moving, in degrees. Must be between 1 and 360 if specified.
    */
  def withHeading(heading: Int): InputLocationMessageContent = copy(heading = Some(heading))

  /** For live locations, a maximum distance for proximity alerts about approaching another chat member, in meters. Must
    * be between 1 and 100000 if specified.
    */
  def withProximityAlertRadius(proximityAlertRadius: Long): InputLocationMessageContent =
    copy(proximityAlertRadius = Some(proximityAlertRadius))
}

object InputVenueMessageContent {

  /** Constructs minimal [[InputVenueMessageContent]]
    * @param latitude
    *   Latitude of the venue in degrees
    * @param longitude
    *   Longitude of the venue in degrees
    * @param title
    *   Name of the venue
    * @param address
    *   Address of the venue
    * @return
    *   [[InputVenueMessageContent]] builder
    */
  def of(latitude: Double, longitude: Double, title: String, address: String): InputVenueMessageContent =
    InputVenueMessageContent(
      latitude = latitude,
      longitude = longitude,
      title = title,
      address = address,
      foursquareId = None,
      foursquareType = None,
      googlePlaceId = None,
      googlePlaceType = None
    )
}

/** Represents the [[https://core.telegram.org/bots/api#inputmessagecontent content]] of a venue message to be sent as
  * the result of an inline query.
  */
case class InputVenueMessageContent(
  latitude: Double,
  longitude: Double,
  title: String,
  address: String,
  foursquareId: Option[String],
  foursquareType: Option[String],
  googlePlaceId: Option[String],
  googlePlaceType: Option[String]
) extends InputMessageContent {

  /** Foursquare identifier of the venue, if known
    */
  def withFoursquareId(foursquareId: String): InputVenueMessageContent = copy(foursquareId = Some(foursquareId))

  /** Foursquare type of the venue, if known. (For example, “arts_entertainment/default”, “arts_entertainment/aquarium”
    * or “food/icecream”.)
    */
  def withFoursquareType(foursquareType: String): InputVenueMessageContent = copy(foursquareType = Some(foursquareType))

  /** Google Places identifier of the venue
    */
  def withGooglePlaceId(googlePlaceId: String): InputVenueMessageContent = copy(googlePlaceId = Some(googlePlaceId))

  /** Google Places type of the venue. (See
    * [[https://developers.google.com/places/web-service/supported_types supported types]].)
    */
  def withGooglePlaceType(googlePlaceType: String): InputVenueMessageContent =
    copy(googlePlaceType = Some(googlePlaceType))
}

object InputContactMessageContent {

  /** Constructs minimal [[InputContactMessageContent]]
    * @param phoneNumber
    *   Contact's phone number
    * @param firstName
    *   Contact's first name
    * @return
    *   [[InputContactMessageContent]] builder
    */
  def of(phoneNumber: String, firstName: String): InputContactMessageContent = InputContactMessageContent(
    phoneNumber = phoneNumber,
    firstName = firstName,
    lastName = None,
    vcard = None
  )
}

/** Represents the [[https://core.telegram.org/bots/api#inputmessagecontent content]] of a contact message to be sent as
  * the result of an inline query.
  */
case class InputContactMessageContent(
  phoneNumber: String,
  firstName: String,
  lastName: Option[String],
  vcard: Option[String]
) extends InputMessageContent {

  /** Contact's last name
    */
  def withLastName(lastName: String): InputContactMessageContent = copy(lastName = Some(lastName))

  /** Additional data about the contact in the form of a [[https://en.wikipedia.org/wiki/VCard vCard]], 0-2048 bytes
    */
  def withVcard(vcard: String): InputContactMessageContent = copy(vcard = Some(vcard))
}

object InputInvoiceMessageContent {

  /** Constructs minimal [[InputInvoiceMessageContent]]
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
    *   [[InputInvoiceMessageContent]] builder
    */
  def of(
    title: String,
    description: String,
    payload: String,
    providerToken: String,
    currency: String,
    prices: Seq[LabeledPrice]
  ): InputInvoiceMessageContent = InputInvoiceMessageContent(
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
}

case class InputInvoiceMessageContent(
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
) extends InputMessageContent {

  /** The maximum accepted amount for tips in the smallest units of the currency (integer, not float/double). For
    * example, for a maximum tip of `US$ 1.45` pass `max_tip_amount = 145`. See the exp parameter in
    * [[https://core.telegram.org/bots/payments/currencies.json currencies.json]], it shows the number of digits past
    * the decimal point for each currency (2 for the majority of currencies). Defaults to 0
    */
  def withMaxTipAmount(maxTipAmount: Long): InputInvoiceMessageContent = copy(maxTipAmount = Some(maxTipAmount))

  /** A JSON-serialized array of suggested amounts of tip in the smallest units of the currency (integer, not
    * float/double). At most 4 suggested tip amounts can be specified. The suggested tip amounts must be positive,
    * passed in a strictly increased order and must not exceed max_tip_amount.
    */
  def withSuggestedTipAmounts(suggestedTipAmounts: Seq[Long]): InputInvoiceMessageContent =
    copy(suggestedTipAmounts = Some(suggestedTipAmounts))

  /** A JSON-serialized object for data about the invoice, which will be shared with the payment provider. A detailed
    * description of the required fields should be provided by the payment provider.
    */
  def withProviderData(providerData: String): InputInvoiceMessageContent = copy(providerData = Some(providerData))

  /** URL of the product photo for the invoice. Can be a photo of the goods or a marketing image for a service.
    */
  def withPhotoUrl(photoUrl: String): InputInvoiceMessageContent = copy(photoUrl = Some(photoUrl))

  /** Photo size in bytes
    */
  def withPhotoSize(photoSize: Long): InputInvoiceMessageContent = copy(photoSize = Some(photoSize))

  /** Photo width
    */
  def withPhotoWidth(photoWidth: Long): InputInvoiceMessageContent = copy(photoWidth = Some(photoWidth))

  /** Photo height
    */
  def withPhotoHeight(photoHeight: Long): InputInvoiceMessageContent = copy(photoHeight = Some(photoHeight))

  /** Pass True if you require the user's full name to complete the order
    */
  def withNeedName(needName: Boolean): InputInvoiceMessageContent = copy(needName = Some(needName))

  /** Pass True if you require the user's phone number to complete the order
    */
  def withNeedPhoneNumber(needPhoneNumber: Boolean): InputInvoiceMessageContent =
    copy(needPhoneNumber = Some(needPhoneNumber))

  /** Pass True if you require the user's email address to complete the order
    */
  def withNeedEmail(needEmail: Boolean): InputInvoiceMessageContent = copy(needEmail = Some(needEmail))

  /** Pass True if you require the user's shipping address to complete the order
    */
  def withNeedShippingAddress(needShippingAddress: Boolean): InputInvoiceMessageContent =
    copy(needShippingAddress = Some(needShippingAddress))

  /** Pass True if the user's phone number should be sent to provider
    */
  def withSendPhoneNumberToProvider(sendPhoneNumberToProvider: Boolean): InputInvoiceMessageContent =
    copy(sendPhoneNumberToProvider = Some(sendPhoneNumberToProvider))

  /** Pass True if the user's email address should be sent to provider
    */
  def withSendEmailToProvider(sendEmailToProvider: Boolean): InputInvoiceMessageContent =
    copy(sendEmailToProvider = Some(sendEmailToProvider))

  /** Pass True if the final price depends on the shipping method
    */
  def withIsFlexible(isFlexible: Boolean): InputInvoiceMessageContent = copy(isFlexible = Some(isFlexible))
}
