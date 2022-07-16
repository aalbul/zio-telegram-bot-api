package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object InlineKeyboardButton {

  /** Constructs minimal [[InlineKeyboardButton]]
    *
    * @param text
    *   Label text on the button
    * @return
    *   [[InlineKeyboardButton]] builder
    */
  def of(text: String): InlineKeyboardButton = InlineKeyboardButton(
    text = text,
    url = None,
    callbackData = None,
    webApp = None,
    loginUrl = None,
    switchInlineQuery = None,
    switchInlineQueryCurrentChat = None,
    callbackGame = None,
    pay = None
  )
}

/** This object represents one button of an inline keyboard. You must use exactly one of the optional fields.
  */
@ConfiguredJsonCodec
case class InlineKeyboardButton(
  text: String,
  url: Option[String],
  callbackData: Option[String],
  webApp: Option[WebAppInfo],
  loginUrl: Option[LoginUrl],
  switchInlineQuery: Option[String],
  switchInlineQueryCurrentChat: Option[String],
  callbackGame: Option[CallbackGame],
  pay: Option[Boolean]
) {

  /** HTTP or tg:// URL to be opened when the button is pressed. Links tg://user?id=<user_id> can be used to mention a
    * user by their ID without using a username, if this is allowed by their privacy settings.
    */
  def withUrl(url: String): InlineKeyboardButton = copy(url = Some(url))

  /** Data to be sent in a [[https://core.telegram.org/bots/api#callbackquery callback query]] to the bot when button is
    * pressed, 1-64 bytes
    */
  def withCallbackData(data: String): InlineKeyboardButton = copy(callbackData = Some(data))

  /** Description of the [[https://core.telegram.org/bots/webapps Web App]] that will be launched when the user presses
    * the button. The Web App will be able to send an arbitrary message on behalf of the user using the method
    * [[AnswerWebAppQuery]]. Available only in private chats between a user and the bot.
    */
  def withWebApp(info: WebAppInfo): InlineKeyboardButton = copy(webApp = Some(info))

  /** An HTTPS URL used to automatically authorize the user. Can be used as a replacement for the
    * [[https://core.telegram.org/widgets/login Telegram Login Widget.]]
    */
  def withLoginUrl(url: LoginUrl): InlineKeyboardButton = copy(loginUrl = Some(url))

  /** If set, pressing the button will prompt the user to select one of their chats, open that chat and insert the bot's
    * username and the specified inline query in the input field. May be empty, in which case just the bot's username
    * will be inserted.
    *
    * Note: This offers an easy way for users to start using your bot in
    * [[https://core.telegram.org/bots/inline inline mode]] when they are currently in a private chat with it.
    * Especially useful when combined with [[https://core.telegram.org/bots/api#answerinlinequery switch_pm…]] actions -
    * in this case the user will be automatically returned to the chat they switched from, skipping the chat selection
    * screen.
    */
  def withSwitchInlineQuery(query: String): InlineKeyboardButton = copy(switchInlineQuery = Some(query))

  /** If set, pressing the button will insert the bot's username and the specified inline query in the current chat's
    * input field. May be empty, in which case only the bot's username will be inserted.
    *
    * This offers a quick way for the user to open your bot in inline mode in the same chat - good for selecting
    * something from multiple options.
    */
  def withSwitchInlineQueryCurrentChat(query: String): InlineKeyboardButton =
    copy(switchInlineQueryCurrentChat = Some(query))

  /** Description of the game that will be launched when the user presses the button.
    *
    * NOTE: This type of button must always be the first button in the first row.
    */
  def withCallbackGame(game: CallbackGame): InlineKeyboardButton = copy(callbackGame = Some(game))

  /** Specify True, to send a Pay button.
    *
    * NOTE: This type of button must always be the first button in the first row and can only be used in invoice
    * messages.
    */
  def withPay(pay: Boolean): InlineKeyboardButton = copy(pay = Some(pay))
}
