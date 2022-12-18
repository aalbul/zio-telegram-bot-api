package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ForceReply {
  implicit val forceReplyJsonCodec: JsonValueCodec[ForceReply] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

case class ForceReply(forceReply: Boolean, inputFieldPlaceholder: Option[String], selective: Option[Boolean])
  extends Markup
