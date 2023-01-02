package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}

object GeneralForumTopicUnhidden {
  implicit val generalForumTopicUnhiddenJsonCodec: JsonValueCodec[GeneralForumTopicUnhidden] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[GeneralForumTopicUnhidden]]
    *
    * @return
    *   [[GeneralForumTopicUnhidden]] builder
    */
  def of: GeneralForumTopicUnhidden = GeneralForumTopicUnhidden()
}

/** This object represents a service message about General forum topic unhidden in the chat. Currently holds no
  * information.
  */
case class GeneralForumTopicUnhidden()
