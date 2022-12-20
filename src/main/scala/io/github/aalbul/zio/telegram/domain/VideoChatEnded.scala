package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import scala.concurrent.duration.Duration

object VideoChatEnded {
  implicit val videoChatEndedJsonCodec: JsonValueCodec[VideoChatEnded] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[VideoChatEnded]]
    * @param duration
    *   Video chat duration in seconds
    * @return
    *   [[VideoChatEnded]] builder
    */
  def of(duration: Duration): VideoChatEnded = VideoChatEnded(
    duration = duration
  )
}

/** This object represents a service message about a video chat ended in the chat.
  */
case class VideoChatEnded(duration: Duration)
