package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ReplyKeyboardRemove {
  implicit val replyKeyboardRemoveJsonCodec: JsonValueCodec[ReplyKeyboardRemove] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[ReplyKeyboardRemove]]
    * @param removeKeyboard
    *   Requests clients to remove the custom keyboard (user will not be able to summon this keyboard; if you want to
    *   hide the keyboard from sight but keep it accessible, use one_time_keyboard in
    *   [[https://core.telegram.org/bots/api#replykeyboardmarkup ReplyKeyboardMarkup]])
    * @return
    *   [[ReplyKeyboardRemove]] builder
    */
  def of(removeKeyboard: Boolean): ReplyKeyboardRemove = ReplyKeyboardRemove(
    removeKeyboard = removeKeyboard,
    selective = None
  )
}

/** Upon receiving a message with this object, Telegram clients will remove the current custom keyboard and display the
  * default letter-keyboard. By default, custom keyboards are displayed until a new keyboard is sent by a bot. An
  * exception is made for one-time keyboards that are hidden immediately after the user presses a button (see
  * [[https://core.telegram.org/bots/api#replykeyboardmarkup ReplyKeyboardMarkup]]).
  */
case class ReplyKeyboardRemove(removeKeyboard: Boolean, selective: Option[Boolean]) extends Markup {

  /** Use this parameter if you want to remove the keyboard for specific users only. Targets: 1) users that are
    * \@mentioned in the text of the [[https://core.telegram.org/bots/api#message Message]] object; 2) if the bot's
    * message is a reply (has reply_to_message_id), sender of the original message.
    *
    * Example: A user votes in a poll, bot returns confirmation message in reply to the vote and removes the keyboard
    * for that user, while still showing the keyboard with poll options to users who haven't voted yet.
    */
  def withSelective(selective: Boolean): ReplyKeyboardRemove = copy(selective = Some(selective))
}
