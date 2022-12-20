package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object KeyboardButton {

  /** Constructs minimal [[KeyboardButton]]
    *
    * @param text
    *   Text of the button. If none of the optional fields are used, it will be sent as a message when the button is
    *   pressed
    * @return
    *   [[KeyboardButton]] builder
    */
  def of(text: String): KeyboardButton = KeyboardButton(
    text = text,
    requestContact = None,
    requestLocation = None,
    requestPoll = None,
    webApp = None
  )

  implicit val keyboardButtonJsonCodec: JsonValueCodec[KeyboardButton] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

/** This object represents one button of the reply keyboard. For simple text buttons String can be used instead of this
  * object to specify text of the button. Optional fields web_app, request_contact, request_location, and request_poll
  * are mutually exclusive.
  *
  * Note: request_contact and request_location options will only work in Telegram versions released after 9 April, 2016.
  * Older clients will display unsupported message.
  *
  * Note: request_poll option will only work in Telegram versions released after 23 January, 2020. Older clients will
  * display unsupported message.
  *
  * Note: web_app option will only work in Telegram versions released after 16 April, 2022. Older clients will display
  * unsupported message.
  */
case class KeyboardButton(
  text: String,
  requestContact: Option[Boolean],
  requestLocation: Option[Boolean],
  requestPoll: Option[KeyboardButtonPollType],
  webApp: Option[WebAppInfo]
) {

  /** If ''True'', the user's phone number will be sent as a contact when the button is pressed. Available in private
    * chats only.
    */
  def withRequestContact(requestContact: Boolean): KeyboardButton = copy(requestContact = Some(requestContact))

  /** If ''True'', the user's current location will be sent when the button is pressed. Available in private chats only.
    */
  def withRequestLocation(requestLocation: Boolean): KeyboardButton = copy(requestLocation = Some(requestLocation))

  /** If specified, the user will be asked to create a poll and send it to the bot when the button is pressed. Available
    * in private chats only.
    */
  def withRequestPoll(requestPoll: KeyboardButtonPollType): KeyboardButton = copy(requestPoll = Some(requestPoll))

  /** If specified, the described [[https://core.telegram.org/bots/webapps Web App]] will be launched when the button is
    * pressed. The Web App will be able to send a “web_app_data” service message. Available in private chats only.
    */
  def withWebApp(webApp: WebAppInfo): KeyboardButton = copy(webApp = Some(webApp))
}
