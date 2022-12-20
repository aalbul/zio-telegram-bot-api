package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object CallbackQuery {
  implicit val callbackQueryJsonCodec: JsonValueCodec[CallbackQuery] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[CallbackQuery]] object
    * @param id
    *   Unique identifier for this query
    * @param from
    *   Sender
    * @param chatInstance
    *   Global identifier, uniquely corresponding to the chat to which the message with the callback button was sent.
    *   Useful for high scores in [[https://core.telegram.org/bots/api#games games]].
    * @return
    *   [[CallbackQuery]] builder
    */
  def of(id: String, from: User, chatInstance: String): CallbackQuery = CallbackQuery(
    id = id,
    from = from,
    message = None,
    inlineMessageId = None,
    chatInstance = chatInstance,
    data = None,
    gameShortName = None
  )
}

/** This object represents an incoming callback query from a callback button in an
  * [[https://core.telegram.org/bots/features#inline-keyboards inline keyboard]]. If the button that originated the
  * query was attached to a `message` sent by the bot, the field `message` will be present. If the button was attached
  * to a message sent via the bot (in [[https://core.telegram.org/bots/api#inline-mode inline mode]]), the field
  * `inline_message_id` will be present. Exactly one of the fields data or `game_short_name` will be present.
  *
  * NOTE: After the user presses a callback button, Telegram clients will display a progress bar until you call
  * [[https://core.telegram.org/bots/api#answercallbackquery answerCallbackQuery]]. It is, therefore, necessary to react
  * by calling [[https://core.telegram.org/bots/api#answercallbackquery answerCallbackQuery]] even if no notification to
  * the user is needed (e.g., without specifying any of the optional parameters).
  */
case class CallbackQuery(
  id: String,
  from: User,
  message: Option[Message],
  inlineMessageId: Option[String],
  chatInstance: String,
  data: Option[String],
  gameShortName: Option[String]
) {

  /** Message with the callback button that originated the query. Note that message content and message date will not be
    * available if the message is too old
    */
  def withMessage(message: Message): CallbackQuery = copy(message = Some(message))

  /** Identifier of the message sent via the bot in inline mode, that originated the query.
    */
  def withInlineMessageId(inlineMessageId: String): CallbackQuery = copy(inlineMessageId = Some(inlineMessageId))

  /** Data associated with the callback button. Be aware that the message originated the query can contain no callback
    * buttons with this data.
    */
  def withData(data: String): CallbackQuery = copy(data = Some(data))

  /** Short name of a [[https://core.telegram.org/bots/api#games Game]] to be returned, serves as the unique identifier
    * for the game
    */
  def withGameShortName(gameShortName: String): CallbackQuery = copy(gameShortName = Some(gameShortName))
}
