package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import java.time.Instant

object VideoChatScheduled {

  /** Constructs minimal [[VideoChatScheduled]]
    * @param startDate
    *   Point in time (Unix timestamp) when the video chat is supposed to be started by a chat administrator
    * @return
    *   [[VideoChatScheduled]] builder
    */
  def of(startDate: Instant): VideoChatScheduled = VideoChatScheduled(
    startDate = startDate
  )

  implicit val videoChatScheduledJsonCodec: JsonValueCodec[VideoChatScheduled] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

/** This object represents a service message about a video chat scheduled in the chat.
  */
case class VideoChatScheduled(startDate: Instant)
