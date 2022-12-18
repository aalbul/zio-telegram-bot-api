package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import enumeratum.{EnumEntry, *}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.codecs
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.codecs.InvariantCodec

sealed trait ParseMode extends EnumEntry

object ParseMode extends Enum[ParseMode] {
  case object Markdown extends ParseMode
  case object MarkdownV2 extends ParseMode
  case object HTML extends ParseMode

  override def values: IndexedSeq[ParseMode] = findValues

  private val mapping = values.map(value => value.toString -> value).toMap

  implicit val parseModeJsonCodec: JsonValueCodec[ParseMode] = codecs.string.imap(mapping)(_.toString)(null)
}
