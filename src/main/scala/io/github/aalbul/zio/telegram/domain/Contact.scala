package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object Contact {
  implicit val contactJsonCodec: JsonValueCodec[Contact] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

case class Contact(
  phoneNumber: String,
  firstName: String,
  lastName: Option[String],
  userId: Option[Long],
  vcard: Option[String]
)
