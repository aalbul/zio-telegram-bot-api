package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}

object ForumTopic {
  implicit val forumTopicJsonCodec: JsonValueCodec[ForumTopic] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[ForumTopic]] object
    * @param messageThreadId
    *   Unique identifier of the forum topic
    * @param name
    *   Name of the topic
    * @param iconColor
    *   Color of the topic icon in RGB format
    * @return
    *   [[ForumTopic]] builder
    */
  def of(messageThreadId: Long, name: String, iconColor: Long): ForumTopic = ForumTopic(
    messageThreadId = messageThreadId,
    name = name,
    iconColor = iconColor,
    iconCustomEmojiId = None
  )
}

case class ForumTopic(messageThreadId: Long, name: String, iconColor: Long, iconCustomEmojiId: Option[String]) {

  /** Unique identifier of the custom emoji shown as the topic icon
    */
  def withIconCustomEmojiId(iconCustomEmojiId: String): ForumTopic = copy(iconCustomEmojiId = Some(iconCustomEmojiId))
}
