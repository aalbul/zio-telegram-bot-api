package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ForumTopicCreated {

  /** Constructs minimal [[ForumTopicCreated]]
    *
    * @param name
    *   Name of the topic
    * @param iconColor
    *   Color of the topic icon in RGB format
    * @return
    *   [[ForumTopicCreated]] builder
    */
  def of(name: String, iconColor: Int): ForumTopicCreated =
    ForumTopicCreated(name = name, iconColor = iconColor, iconCustomEmojiId = None)
}

/** This object represents a service message about a new forum topic created in the chat.
  */
@ConfiguredJsonCodec(decodeOnly = true)
case class ForumTopicCreated(name: String, iconColor: Int, iconCustomEmojiId: Option[String]) {

  /** Unique identifier of the custom emoji shown as the topic icon
    */
  def withIconCustomEmojiId(iconCustomEmojiId: String): ForumTopicCreated =
    copy(iconCustomEmojiId = Some(iconCustomEmojiId))
}
