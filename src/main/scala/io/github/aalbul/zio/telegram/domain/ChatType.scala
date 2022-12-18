package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import enumeratum.{EnumEntry, *}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

sealed trait ChatType extends EnumEntry

object ChatType extends Enum[ChatType] {
  case object Sender extends ChatType
  case object Private extends ChatType
  case object Group extends ChatType
  case object Supergroup extends ChatType
  case object Channel extends ChatType

  override def values: IndexedSeq[ChatType] = findValues

  implicit lazy val chatTypeJsonCodec: JsonValueCodec[ChatType] = codecs.makeEnumCodec(values)
}
