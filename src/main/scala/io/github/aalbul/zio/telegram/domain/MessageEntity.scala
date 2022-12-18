package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object MessageEntity {
  implicit val messageEntityJsonCodec: JsonValueCodec[MessageEntity] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  implicit val seqMessageEntityJsonCodec: JsonValueCodec[Seq[MessageEntity]] = JsonCodecMaker.make
}

case class MessageEntity(
  `type`: MessageEntityType,
  offset: Int,
  length: Int,
  url: Option[String],
  user: Option[User],
  language: Option[String]
)
