package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ForumTopicReopened {

  /** Constructs minimal [[ForumTopicReopened]]
    *
    * @return
    *   [[ForumTopicReopened]] builder
    */
  def of(): ForumTopicReopened = ForumTopicReopened()

  implicit val forumTopicReopenedJsonCodec: JsonValueCodec[ForumTopicReopened] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

/** This object represents a service message about a forum topic reopened in the chat. Currently holds no information.
  */
case class ForumTopicReopened()
