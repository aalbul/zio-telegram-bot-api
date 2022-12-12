package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import scala.concurrent.duration.Duration

object VideoChatEnded {

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
@ConfiguredJsonCodec(decodeOnly = true)
case class VideoChatEnded(duration: Duration)
