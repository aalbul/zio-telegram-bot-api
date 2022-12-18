package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ChosenInlineResult {
  implicit val chosenInlineResultJsonCodec: JsonValueCodec[ChosenInlineResult] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

case class ChosenInlineResult(
  resultId: String,
  from: User,
  location: Option[Location],
  inlineMessageId: Option[String],
  query: String
)
