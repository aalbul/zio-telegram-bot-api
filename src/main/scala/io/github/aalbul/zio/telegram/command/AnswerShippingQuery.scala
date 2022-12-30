package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.AnswerShippingQuery.AnswerShippingQueryPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.ShippingOption

object AnswerShippingQuery {
  object AnswerShippingQueryPayload {
    implicit val answerShippingQueryPayloadJsonCodec: JsonValueCodec[AnswerShippingQueryPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class AnswerShippingQueryPayload(
    shippingQueryId: String,
    ok: Boolean,
    shippingOptions: Option[Seq[ShippingOption]],
    errorMessage: Option[String]
  )

  /** Constructs minimal [[AnswerShippingQuery]] command
    * @param shippingQueryId
    *   Unique identifier for the query to be answered
    * @param ok
    *   Pass True if delivery to the specified address is possible and False if there are any problems (for example, if
    *   delivery to the specified address is not possible)
    * @return
    *   [[AnswerShippingQuery]] builder
    */
  def of(shippingQueryId: String, ok: Boolean): AnswerShippingQuery = AnswerShippingQuery(
    AnswerShippingQueryPayload(
      shippingQueryId = shippingQueryId,
      ok = ok,
      shippingOptions = None,
      errorMessage = None
    )
  )
}

/** If you sent an invoice requesting a shipping address and the parameter is_flexible was specified, the Bot API will
  * send an [[https://core.telegram.org/bots/api#update Update]] with a shipping_query field to the bot. Use this method
  * to reply to shipping queries. On success, True is returned.
  */
case class AnswerShippingQuery(payload: AnswerShippingQueryPayload) extends Command[Boolean] {
  override val name: String = "answerShippingQuery"
  override def parameters: ApiParameters = JsonBody(payload)

  /** Required if ok is True. A JSON-serialized array of available shipping options.
    */
  def withShippingOptions(shippingOptions: Seq[ShippingOption]): AnswerShippingQuery = copy(
    payload.copy(shippingOptions = Some(shippingOptions))
  )

  /** Required if ok is False. Error message in human readable form that explains why it is impossible to complete the
    * order (e.g. "Sorry, delivery to your desired address is unavailable'). Telegram will display this message to the
    * user.
    */
  def withErrorMessage(errorMessage: String): AnswerShippingQuery = copy(
    payload.copy(errorMessage = Some(errorMessage))
  )
}
