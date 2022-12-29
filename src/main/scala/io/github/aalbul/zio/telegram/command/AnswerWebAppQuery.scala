package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.AnswerWebAppQuery.AnswerWebAppQueryPayload
import io.github.aalbul.zio.telegram.domain.{InlineQueryResult, SentWebAppMessage}

object AnswerWebAppQuery {
  object AnswerWebAppQueryPayload {
    implicit val answerWebAppQueryPayloadJsonCodec: JsonValueCodec[AnswerWebAppQueryPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class AnswerWebAppQueryPayload(webAppQueryId: String, result: InlineQueryResult)

  /** Constructs minimal [[AnswerWebAppQuery]] command
    * @param webAppQueryId
    *   Unique identifier for the query to be answered
    * @param result
    *   A JSON-serialized object describing the message to be sent
    * @return
    *   [[AnswerWebAppQuery]] builder
    */
  def of(webAppQueryId: String, result: InlineQueryResult): AnswerWebAppQuery = AnswerWebAppQuery(
    AnswerWebAppQueryPayload(webAppQueryId = webAppQueryId, result = result)
  )
}

/** Use this method to set the result of an interaction with a [[https://core.telegram.org/bots/webapps Web App]] and
  * send a corresponding message on behalf of the user to the chat from which the query originated. On success, a
  * [[https://core.telegram.org/bots/api#sentwebappmessage SentWebAppMessage]] object is returned.
  */
case class AnswerWebAppQuery(payload: AnswerWebAppQueryPayload) extends Command[SentWebAppMessage] {
  override val name: String = "answerWebAppQuery"
  override def parameters: ApiParameters = JsonBody(payload)
}
