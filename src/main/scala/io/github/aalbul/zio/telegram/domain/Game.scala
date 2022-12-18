package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object Game {
  implicit val gameJsonCodec: JsonValueCodec[Game] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

case class Game(
  title: String,
  description: String,
  photo: Seq[PhotoSize],
  text: Option[String],
  textEntities: Option[Seq[MessageEntity]],
  animation: Option[Animation]
)
