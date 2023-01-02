package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}

object GeneralForumTopicHidden {
  implicit val generalForumTopicHiddenJsonCodec: JsonValueCodec[GeneralForumTopicHidden] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[GeneralForumTopicHidden]]
    *
    * @return
    *   [[GeneralForumTopicHidden]] builder
    */
  def of: GeneralForumTopicHidden = GeneralForumTopicHidden()
}

/** This object represents a service message about General forum topic hidden in the chat. Currently holds no
  * information.
  */
case class GeneralForumTopicHidden()
