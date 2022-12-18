package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import enumeratum.{EnumEntry, *}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.codecs

sealed trait StickerType extends EnumEntry

object StickerType extends Enum[StickerType] {
  case object Regular extends StickerType
  case object Mask extends StickerType
  case object CustomEmoji extends StickerType

  override def values: IndexedSeq[StickerType] = findValues

  implicit val stickerTypeJsonCodec: JsonValueCodec[StickerType] = codecs.makeEnumCodec(values)
}
