package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import enumeratum.{EnumEntry, *}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.serialization.InvariantCodec.JsonValueCodecOps

sealed trait ParseMode extends EnumEntry

object ParseMode extends Enum[ParseMode] {
  case object Markdown extends ParseMode
  case object MarkdownV2 extends ParseMode
  case object HTML extends ParseMode

  override def values: IndexedSeq[ParseMode] = findValues

  private val mapping = values.map(value => value.toString -> value).toMap

  implicit val parseModeJsonCodec: JsonValueCodec[ParseMode] = stringJsonCodec.imap(mapping)(_.toString)(null)
}
