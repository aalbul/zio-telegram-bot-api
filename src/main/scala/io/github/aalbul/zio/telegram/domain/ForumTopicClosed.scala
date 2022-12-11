package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ForumTopicClosed {

  /** Constructs minimal [[ForumTopicClosed]]
    *
    * @return
    *   [[ForumTopicClosed]] builder
    */
  def of(): ForumTopicClosed = ForumTopicClosed()
}

/** This object represents a service message about a forum topic closed in the chat. Currently holds no information.
  */
@ConfiguredJsonCodec(decodeOnly = true)
case class ForumTopicClosed()
