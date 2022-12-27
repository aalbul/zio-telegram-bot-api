package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.AnswerCallbackQuery.AnswerCallbackQueryPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import scala.concurrent.duration.Duration

object AnswerCallbackQuery {
  object AnswerCallbackQueryPayload {
    implicit val answerCallbackQueryPayloadJsonCodec: JsonValueCodec[AnswerCallbackQueryPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class AnswerCallbackQueryPayload(
    callbackQueryId: String,
    text: Option[String],
    showAlert: Option[Boolean],
    url: Option[String],
    cacheTime: Option[Duration]
  )

  /** Constructs minimal [[AnswerCallbackQuery]] command
    * @param callbackQueryId
    *   Unique identifier for the query to be answered
    * @return
    *   [[AnswerCallbackQuery]] builder
    */
  def of(callbackQueryId: String): AnswerCallbackQuery = AnswerCallbackQuery(
    AnswerCallbackQueryPayload(
      callbackQueryId = callbackQueryId,
      text = None,
      showAlert = None,
      url = None,
      cacheTime = None
    )
  )
}

/** Use this method to send answers to callback queries sent from
  * [[https://core.telegram.org/bots/features#inline-keyboards inline keyboards]]. The answer will be displayed to the
  * user as a notification at the top of the chat screen or as an alert. On success, True is returned.
  *
  * Alternatively, the user can be redirected to the specified Game URL. For this option to work, you must first create
  * a game for your bot via [[https://t.me/botfather @BotFather]] and accept the terms. Otherwise, you may use links
  * like `t.me/your_bot?start=XXXX` that open your bot with a parameter.
  */
case class AnswerCallbackQuery(payload: AnswerCallbackQueryPayload) extends Command[Boolean] {
  override val name: String = "answerCallbackQuery"
  override def parameters: ApiParameters = JsonBody(payload)

  /** Text of the notification. If not specified, nothing will be shown to the user, 0-200 characters
    */
  def withText(text: String): AnswerCallbackQuery = copy(payload.copy(text = Some(text)))

  /** If True, an alert will be shown by the client instead of a notification at the top of the chat screen. Defaults to
    * false.
    */
  def withShowAlert(showAlert: Boolean): AnswerCallbackQuery = copy(payload.copy(showAlert = Some(showAlert)))

  /** URL that will be opened by the user's client. If you have created a
    * [[https://core.telegram.org/bots/api#game Game]] and accepted the conditions via
    * [[https://t.me/botfather @BotFather]], specify the URL that opens your game - note that this will only work if the
    * query comes from a [[https://core.telegram.org/bots/api#inlinekeyboardbutton callback_game]] button.
    *
    * Otherwise, you may use links like `t.me/your_bot?start=XXXX` that open your bot with a parameter.
    */
  def withUrl(url: String): AnswerCallbackQuery = copy(payload.copy(url = Some(url)))

  /** The maximum amount of time in seconds that the result of the callback query may be cached client-side. Telegram
    * apps will support caching starting in version 3.14. Defaults to 0.
    */
  def withCacheTime(cacheTime: Duration): AnswerCallbackQuery = copy(payload.copy(cacheTime = Some(cacheTime)))
}
