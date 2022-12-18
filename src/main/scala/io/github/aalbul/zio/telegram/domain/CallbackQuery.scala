package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object CallbackQuery {
  implicit val callbackQueryJsonCodec: JsonValueCodec[CallbackQuery] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

case class CallbackQuery(
  id: String,
  from: User,
  message: Option[Message],
  inlineMessageId: Option[String],
  chatInstance: String,
  data: Option[String],
  gameShortName: Option[String]
)
