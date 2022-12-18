package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.{JsonReader, JsonValueCodec, JsonWriter}
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.codecs.InvariantCodec

import java.time.Instant
import java.util.concurrent.TimeUnit
import scala.concurrent.duration.Duration

object JsonSerializationSupport {
  object codecs {

    implicit class InvariantCodec[A](valueCodec: JsonValueCodec[A]) {
      def imap[B](f: A => B)(g: B => A)(defaultValue: B): JsonValueCodec[B] = new JsonValueCodec[B] {
        override def decodeValue(in: JsonReader, default: B): B = f(valueCodec.decodeValue(in, valueCodec.nullValue))
        override def encodeValue(x: B, out: JsonWriter): Unit = valueCodec.encodeValue(g(x), out)
        override def nullValue: B = defaultValue
      }
    }

    implicit val string: JsonValueCodec[String] = JsonCodecMaker.make
    implicit val long: JsonValueCodec[Long] = JsonCodecMaker.make
    implicit val boolean: JsonValueCodec[Boolean] = JsonCodecMaker.make

    def makeEnumCodec[T >: Null](set: Iterable[T]): JsonValueCodec[T] = {
      lazy val indexed = set
        .map(value => value.toString.camelToSnakeCase -> value)
        .toMap
      lazy val reverseIndex = indexed.toSeq.map(_.swap).toMap

      codecs.string.imap(indexed)(reverseIndex)(null)
    }

    def makeCodecFromRelations[T >: Null](relations: (String, T)*): JsonValueCodec[T] = {
      lazy val indexed = relations.toMap
      lazy val reverseIndex = indexed.toSeq.map(_.swap).toMap
      codecs.string.imap(indexed)(reverseIndex)(null)
    }
  }

  implicit val instantJsonCodec: JsonValueCodec[Instant] =
    codecs.long.imap(Instant.ofEpochSecond)(_.getEpochSecond)(null)

  implicit val durationJsonCodec: JsonValueCodec[Duration] =
    codecs.long.imap[Duration](Duration.apply(_, TimeUnit.SECONDS))(_.toSeconds)(null)

  implicit class StringOps(string: String) {
    def camelToSnakeCase: String = string.split("(?<=.)(?=\\p{Lu})").map(_.toLowerCase).mkString("_")
  }
}
