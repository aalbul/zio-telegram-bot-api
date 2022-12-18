package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ChatPhoto {
  implicit val chatPhotoJsonCodec: JsonValueCodec[ChatPhoto] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

case class ChatPhoto(smallFileId: String, smallFileUniqueId: String, bigFileId: String, bigFileUniqueId: String)
