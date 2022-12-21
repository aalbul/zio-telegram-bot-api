package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import enumeratum.{EnumEntry, *}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

sealed trait MaskPointType extends EnumEntry

object MaskPointType extends Enum[MaskPointType] {
  case object Forehead extends MaskPointType
  case object Eyes extends MaskPointType
  case object Mouth extends MaskPointType
  case object Chin extends MaskPointType

  override def values: IndexedSeq[MaskPointType] = findValues

  implicit val markPointTypeJsonCodec: JsonValueCodec[MaskPointType] = makeEnumCodec(values)
}
