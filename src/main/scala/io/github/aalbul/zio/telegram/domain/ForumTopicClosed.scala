package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ForumTopicClosed {
  implicit val forumTopicClosedJsonCodec: JsonValueCodec[ForumTopicClosed] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[ForumTopicClosed]]
    *
    * @return
    *   [[ForumTopicClosed]] builder
    */
  def of: ForumTopicClosed = ForumTopicClosed()
}

/** This object represents a service message about a forum topic closed in the chat. Currently holds no information.
  */
case class ForumTopicClosed()
