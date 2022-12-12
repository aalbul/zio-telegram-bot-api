package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
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
}

/** This object represents a service message about a video chat scheduled in the chat.
  */
@ConfiguredJsonCodec(decodeOnly = true)
case class VideoChatScheduled(startDate: Instant)
