package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object Document {
  implicit val documentJsonCodec: JsonValueCodec[Document] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

case class Document(
  fileId: String,
  fileUniqueId: String,
  thumb: Option[PhotoSize],
  fileName: Option[String],
  mimeType: Option[String],
  fileSize: Option[Int]
)
