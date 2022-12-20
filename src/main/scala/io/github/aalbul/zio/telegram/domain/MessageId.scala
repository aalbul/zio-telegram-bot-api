package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object MessageId {
  implicit val messageIdJsonCodec: JsonValueCodec[MessageId] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[MessageId]]
    * @param messageId
    *   Unique message identifier
    * @return
    *   [[MessageId]] builder
    */
  def of(messageId: Long): MessageId = MessageId(messageId = messageId)
}

/** This object represents a unique message identifier.
  */
case class MessageId(messageId: Long)
