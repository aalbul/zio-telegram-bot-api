package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object PreCheckoutQuery {
  implicit val preCheckoutQueryJsonCodec: JsonValueCodec[PreCheckoutQuery] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

case class PreCheckoutQuery(
  id: String,
  from: User,
  currency: String,
  totalAmount: Int,
  invoicePayload: String,
  shippingOptionId: Option[String],
  orderInfo: Option[OrderInfo]
)
