package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import io.github.aalbul.zio.telegram.serialization.DefaultJsonCodecs
import io.github.aalbul.zio.telegram.serialization.InvariantCodec.JsonValueCodecOps

object JsonSerializationSupport extends DefaultJsonCodecs {
  def makeEnumCodec[T >: Null](set: Iterable[T]): JsonValueCodec[T] = {
    lazy val indexed = set
      .map(value => value.toString.camelToSnakeCase -> value)
      .toMap
    lazy val reverseIndex = indexed.toSeq.map(_.swap).toMap

    stringJsonCodec.imap(indexed)(reverseIndex)(null)
  }

  def makeCodecFromRelations[T >: Null](relations: (String, T)*): JsonValueCodec[T] = {
    lazy val indexed = relations.toMap
    lazy val reverseIndex = indexed.toSeq.map(_.swap).toMap
    stringJsonCodec.imap(indexed)(reverseIndex)(null)
  }

  implicit class StringOps(string: String) {
    def camelToSnakeCase: String = string.split("(?<=.)(?=\\p{Lu})").map(_.toLowerCase).mkString("_")
  }
}
