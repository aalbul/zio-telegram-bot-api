package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object Audio {
  implicit val audioJsonCodec: JsonValueCodec[Audio] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

case class Audio(
  fileId: String,
  fileUniqueId: String,
  duration: Int,
  performer: Option[String],
  title: Option[String],
  fileName: Option[String],
  mimeType: Option[String],
  fileSize: Option[Int],
  thumb: Option[PhotoSize]
)
