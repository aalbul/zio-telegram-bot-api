package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ReplyKeyboardRemove {
  implicit val replyKeyboardRemoveJsonCodec: JsonValueCodec[ReplyKeyboardRemove] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

case class ReplyKeyboardRemove(removeKeyboard: Boolean, selective: Option[Boolean]) extends Markup
