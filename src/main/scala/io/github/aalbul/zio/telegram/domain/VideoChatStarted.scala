package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object VideoChatStarted {

  /** Constructs minimal [[VideoChatStarted]]
    * @return
    *   [[VideoChatStarted]] builder
    */
  def of(): VideoChatStarted = VideoChatStarted()
}

/** This object represents a service message about a video chat started in the chat. Currently holds no information.
  */
@ConfiguredJsonCodec(decodeOnly = true)
case class VideoChatStarted()
