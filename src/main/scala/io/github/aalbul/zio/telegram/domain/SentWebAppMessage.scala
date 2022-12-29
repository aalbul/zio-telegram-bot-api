package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}

object SentWebAppMessage {
  implicit val sentWebAppMessageJsonCodec: JsonValueCodec[SentWebAppMessage] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[SentWebAppMessage]]
    * @return
    *   [[SentWebAppMessage]] builder
    */
  def of: SentWebAppMessage = SentWebAppMessage(inlineMessageId = None)
}

/** Describes an inline message sent by a [[https://core.telegram.org/bots/webapps Web App]] on behalf of a user.
  */
case class SentWebAppMessage(inlineMessageId: Option[String]) {

  /** Identifier of the sent inline message. Available only if there is an
    * [[https://core.telegram.org/bots/api#inlinekeyboardmarkup inline keyboard]] attached to the message.
    */
  def withInlineMessageId(inlineMessageId: String): SentWebAppMessage = copy(inlineMessageId = Some(inlineMessageId))
}
