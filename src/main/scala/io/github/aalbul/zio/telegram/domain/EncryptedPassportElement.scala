package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object EncryptedPassportElement {
  implicit val encryptedPassportElementJsonCodec: JsonValueCodec[EncryptedPassportElement] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

case class EncryptedPassportElement(
  `type`: PassportElementType,
  data: Option[String],
  phoneNumber: Option[String],
  email: Option[String],
  files: Option[Seq[PassportFile]],
  frontSide: Option[PassportFile],
  reverseSide: Option[PassportFile],
  selfie: Option[PassportFile],
  translation: Option[Seq[PassportFile]],
  hash: String
)
