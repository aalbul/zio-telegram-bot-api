package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.AnswerPreCheckoutQuery.AnswerPreCheckoutQueryPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object AnswerPreCheckoutQuery {
  object AnswerPreCheckoutQueryPayload {
    implicit val answerPreCheckoutQueryPayloadJsonCodec: JsonValueCodec[AnswerPreCheckoutQueryPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class AnswerPreCheckoutQueryPayload(preCheckoutQueryId: String, ok: Boolean, errorMessage: Option[String])

  /** Constructs minimal [[AnswerPreCheckoutQuery]] command
    * @param preCheckoutQueryId
    *   Unique identifier for the query to be answered
    * @param ok
    *   Specify True if everything is alright (goods are available, etc.) and the bot is ready to proceed with the
    *   order. Use False if there are any problems.
    * @return
    *   [[AnswerPreCheckoutQuery]] builder
    */
  def of(preCheckoutQueryId: String, ok: Boolean): AnswerPreCheckoutQuery = AnswerPreCheckoutQuery(
    AnswerPreCheckoutQueryPayload(
      preCheckoutQueryId = preCheckoutQueryId,
      ok = ok,
      errorMessage = None
    )
  )
}

/** Once the user has confirmed their payment and shipping details, the Bot API sends the final confirmation in the form
  * of an [[https://core.telegram.org/bots/api#update Update]] with the field pre_checkout_query. Use this method to
  * respond to such pre-checkout queries. On success, True is returned. Note: The Bot API must receive an answer within
  * 10 seconds after the pre-checkout query was sent.
  */
case class AnswerPreCheckoutQuery(payload: AnswerPreCheckoutQueryPayload) extends Command[Boolean] {
  override val name: String = "answerPreCheckoutQuery"
  override def parameters: ApiParameters = JsonBody(payload)

  /** Required if ok is False. Error message in human readable form that explains the reason for failure to proceed with
    * the checkout (e.g. "Sorry, somebody just bought the last of our amazing black T-shirts while you were busy filling
    * out your payment details. Please choose a different color or garment!"). Telegram will display this message to the
    * user.
    */
  def withErrorMessage(errorMessage: String): AnswerPreCheckoutQuery = copy(
    payload.copy(errorMessage = Some(errorMessage))
  )
}
