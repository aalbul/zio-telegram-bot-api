package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import enumeratum.{EnumEntry, *}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

sealed trait PollType extends EnumEntry

object PollType extends Enum[PollType] {
  case object Regular extends PollType
  case object Quiz extends PollType

  override def values: IndexedSeq[PollType] = findValues

  implicit val pollTypeJsonCodec: JsonValueCodec[PollType] = codecs.makeEnumCodec(values)
}
