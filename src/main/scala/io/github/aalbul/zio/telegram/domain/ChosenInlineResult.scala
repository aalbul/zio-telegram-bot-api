package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ChosenInlineResult {
  implicit val chosenInlineResultJsonCodec: JsonValueCodec[ChosenInlineResult] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[ChosenInlineResult]] object
    * @param resultId
    *   The unique identifier for the result that was chosen
    * @param from
    *   The user that chose the result
    * @param query
    *   The query that was used to obtain the result
    * @return
    *   [[ChosenInlineResult]] builder
    */
  def of(resultId: String, from: User, query: String): ChosenInlineResult = ChosenInlineResult(
    resultId = resultId,
    from = from,
    location = None,
    inlineMessageId = None,
    query = query
  )
}

/** Represents a [[https://core.telegram.org/bots/api#inlinequeryresult result]] of an inline query that was chosen by
  * the user and sent to their chat partner.
  *
  * Note: It is necessary to enable [[https://core.telegram.org/bots/inline#collecting-feedback inline feedback]] via
  * [[https://t.me/botfather @BotFather]] in order to receive these objects in updates.
  */
case class ChosenInlineResult(
  resultId: String,
  from: User,
  location: Option[Location],
  inlineMessageId: Option[String],
  query: String
) {

  /** Sender location, only for bots that require user location
    */
  def withLocation(location: Location): ChosenInlineResult = copy(location = Some(location))

  /** Identifier of the sent inline message. Available only if there is an
    * [[https://core.telegram.org/bots/api#inlinekeyboardmarkup inline keyboard]] attached to the message. Will be also
    * received in [[https://core.telegram.org/bots/api#callbackquery callback queries]] and can be used to
    * [[https://core.telegram.org/bots/api#updating-messages edit]] the message.
    */
  def withInlineMessageId(inlineMessageId: String): ChosenInlineResult = copy(inlineMessageId = Some(inlineMessageId))
}
