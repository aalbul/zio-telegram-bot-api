package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ForumTopicReopened {

  /** Constructs minimal [[ForumTopicReopened]]
    *
    * @return
    *   [[ForumTopicReopened]] builder
    */
  def of(): ForumTopicReopened = ForumTopicReopened()
}

/** This object represents a service message about a forum topic reopened in the chat. Currently holds no information.
  */
@ConfiguredJsonCodec(decodeOnly = true)
case class ForumTopicReopened()
