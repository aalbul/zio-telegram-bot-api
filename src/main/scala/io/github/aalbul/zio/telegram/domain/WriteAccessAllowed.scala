package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}

object WriteAccessAllowed {
  implicit val writeAccessAllowedJsonCodec: JsonValueCodec[WriteAccessAllowed] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[WriteAccessAllowed]]
    *
    * @return
    *   [[WriteAccessAllowed]] builder
    */
  def of: WriteAccessAllowed = WriteAccessAllowed()
}

/** This object represents a service message about a user allowing a bot added to the attachment menu to write messages.
  * Currently holds no information.
  */
case class WriteAccessAllowed()
