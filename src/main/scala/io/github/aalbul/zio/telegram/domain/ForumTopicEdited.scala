package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}

object ForumTopicEdited {
  implicit val forumTopicEditedJsonCodec: JsonValueCodec[ForumTopicEdited] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[ForumTopicEdited]]
    * @return
    *   [[ForumTopicEdited]] builder
    */
  def of: ForumTopicEdited = ForumTopicEdited(
    name = None,
    iconCustomEmojiId = None
  )
}

/** This object represents a service message about an edited forum topic.
  */
case class ForumTopicEdited(name: Option[String], iconCustomEmojiId: Option[String]) {

  /** New name of the topic, if it was edited
    */
  def withName(name: String): ForumTopicEdited = copy(name = Some(name))

  /** New identifier of the custom emoji shown as the topic icon, if it was edited; an empty string if the icon was
    * removed
    */
  def withIconCustomEmojiId(iconCustomEmojiId: String): ForumTopicEdited =
    copy(iconCustomEmojiId = Some(iconCustomEmojiId))
}
