package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.AnswerInlineQuery.AnswerInlineQueryPayload
import io.github.aalbul.zio.telegram.domain.InlineQueryResult
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import scala.concurrent.duration.Duration

object AnswerInlineQuery {
  object AnswerInlineQueryPayload {
    implicit val answerInlineQueryPayloadJsonCodec: JsonValueCodec[AnswerInlineQueryPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class AnswerInlineQueryPayload(
    inlineQueryId: String,
    results: Seq[InlineQueryResult],
    cacheTime: Option[Duration],
    isPersonal: Option[Boolean],
    nextOffset: Option[String],
    switchPmText: Option[String],
    switchPmParameter: Option[String]
  )

  /** Constructs minimal [[AnswerInlineQuery]] command
    * @param inlineQueryId
    *   Unique identifier for the answered query
    * @param results
    *   A JSON-serialized array of results for the inline query
    * @return
    *   [[AnswerInlineQuery]] builder
    */
  def of(inlineQueryId: String, results: Seq[InlineQueryResult]): AnswerInlineQuery = AnswerInlineQuery(
    AnswerInlineQueryPayload(
      inlineQueryId = inlineQueryId,
      results = results,
      cacheTime = None,
      isPersonal = None,
      nextOffset = None,
      switchPmText = None,
      switchPmParameter = None
    )
  )
}

/** Use this method to send answers to an inline query. On success, True is returned. No more than 50 results per query
  * are allowed.
  */
case class AnswerInlineQuery(payload: AnswerInlineQueryPayload) extends Command[Boolean] {
  override val name: String = "answerInlineQuery"
  override def parameters: ApiParameters = JsonBody(payload)

  /** The maximum amount of time in seconds that the result of the inline query may be cached on the server. Defaults to
    * 300.
    */
  def withCacheTime(cacheTime: Duration): AnswerInlineQuery = copy(payload.copy(cacheTime = Some(cacheTime)))

  /** Pass True if results may be cached on the server side only for the user that sent the query. By default, results
    * may be returned to any user who sends the same query
    */
  def withIsPersonal(isPersonal: Boolean): AnswerInlineQuery = copy(payload.copy(isPersonal = Some(isPersonal)))

  /** Pass the offset that a client should send in the next query with the same text to receive more results. Pass an
    * empty string if there are no more results or if you don't support pagination. Offset length can't exceed 64 bytes.
    */
  def withNextOffset(nextOffset: String): AnswerInlineQuery = copy(payload.copy(nextOffset = Some(nextOffset)))

  /** If passed, clients will display a button with specified text that switches the user to a private chat with the bot
    * and sends the bot a start message with the parameter switch_pm_parameter
    */
  def withSwitchPmText(switchPmText: String): AnswerInlineQuery = copy(payload.copy(switchPmText = Some(switchPmText)))

  /** [[https://core.telegram.org/bots/features#deep-linking Deep-linking]] parameter for the /start message sent to the
    * bot when user presses the switch button. 1-64 characters, only `A-Z`, `a-z`, `0-9`, `_` and `-` are allowed.
    *
    * Example: An inline bot that sends YouTube videos can ask the user to connect the bot to their YouTube account to
    * adapt search results accordingly. To do this, it displays a 'Connect your YouTube account' button above the
    * results, or even before showing any. The user presses the button, switches to a private chat with the bot and, in
    * doing so, passes a start parameter that instructs the bot to return an OAuth link. Once done, the bot can offer a
    * [[https://core.telegram.org/bots/api#inlinekeyboardmarkup switch_inline]] button so that the user can easily
    * return to the chat where they wanted to use the bot's inline capabilities.
    */
  def withSwitchPmParameter(switchPmParameter: String): AnswerInlineQuery = copy(
    payload.copy(switchPmParameter = Some(switchPmParameter))
  )
}
