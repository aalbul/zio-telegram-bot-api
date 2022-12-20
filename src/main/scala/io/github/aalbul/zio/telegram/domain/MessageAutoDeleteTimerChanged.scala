package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import scala.concurrent.duration.Duration

object MessageAutoDeleteTimerChanged {
  implicit val messageAutoDeleteTimerChangedJsonCodec: JsonValueCodec[MessageAutoDeleteTimerChanged] =
    JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))

  /** Constructs minimal [[MessageAutoDeleteTimerChanged]]
    * @param messageAutoDeleteTime
    *   New auto-delete time for messages in the chat; in seconds
    * @return
    *   [[MessageAutoDeleteTimerChanged]] builder
    */
  def of(messageAutoDeleteTime: Duration): MessageAutoDeleteTimerChanged =
    MessageAutoDeleteTimerChanged(messageAutoDeleteTime = messageAutoDeleteTime)
}

/** This object represents a service message about a change in auto-delete timer settings.
  */
case class MessageAutoDeleteTimerChanged(messageAutoDeleteTime: Duration)
