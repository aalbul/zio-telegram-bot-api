package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ReplyKeyboardMarkup {

  /** Constructs minimal [[ReplyKeyboardMarkup]]
    * @param keyboard
    *   Array of button rows, each represented by an Array of [[KeyboardButton]] objects
    * @return
    *   [[ReplyKeyboardMarkup]] builder
    */
  def of(keyboard: Seq[Seq[KeyboardButton]]): ReplyKeyboardMarkup = ReplyKeyboardMarkup(
    keyboard = keyboard,
    resizeKeyboard = None,
    oneTimeKeyboard = None,
    inputFieldPlaceholder = None,
    selective = None
  )

  implicit val replyKeyboardMarkupJsonCodec: JsonValueCodec[ReplyKeyboardMarkup] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

/** This object represents a [[https://core.telegram.org/bots#keyboards custom keyboard]] with reply options (see
  * [[https://core.telegram.org/bots#keyboards Introduction to bots]] for details and examples).
  */
case class ReplyKeyboardMarkup(
  keyboard: Seq[Seq[KeyboardButton]],
  resizeKeyboard: Option[Boolean],
  oneTimeKeyboard: Option[Boolean],
  inputFieldPlaceholder: Option[String],
  selective: Option[Boolean]
) extends Markup {

  /** Requests clients to resize the keyboard vertically for optimal fit (e.g., make the keyboard smaller if there are
    * just two rows of buttons). Defaults to ''false'', in which case the custom keyboard is always of the same height
    * as the app's standard keyboard.
    */
  def withResizeKeyboard(resizeKeyboard: Boolean): ReplyKeyboardMarkup = copy(resizeKeyboard = Some(resizeKeyboard))

  /** Requests clients to hide the keyboard as soon as it's been used. The keyboard will still be available, but clients
    * will automatically display the usual letter-keyboard in the chat - the user can press a special button in the
    * input field to see the custom keyboard again. Defaults to ''false''.
    */
  def withOneTimeKeyboard(oneTimeKeyboard: Boolean): ReplyKeyboardMarkup = copy(oneTimeKeyboard = Some(oneTimeKeyboard))

  /** The placeholder to be shown in the input field when the keyboard is active; 1-64 characters */
  def withInputFieldPlaceholder(inputFieldPlaceholder: String): ReplyKeyboardMarkup =
    copy(inputFieldPlaceholder = Some(inputFieldPlaceholder))

  /** Use this parameter if you want to show the keyboard to specific users only. Targets: 1) users that are @mentioned
    * in the text of the [[Message]] object; 2) if the bot's message is a reply (has ''reply_to_message_id''), sender of
    * the original message.
    *
    * Example: A user requests to change the bot's language, bot replies to the request with a keyboard to select the
    * new language. Other users in the group don't see the keyboard.
    */
  def withSelective(selective: Boolean): ReplyKeyboardMarkup = copy(selective = Some(selective))
}
