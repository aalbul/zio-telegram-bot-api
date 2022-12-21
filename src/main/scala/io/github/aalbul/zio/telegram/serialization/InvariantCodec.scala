package io.github.aalbul.zio.telegram.serialization

import com.github.plokhotnyuk.jsoniter_scala.core.{JsonReader, JsonValueCodec, JsonWriter}

object InvariantCodec {
  implicit class JsonValueCodecOps[A](valueCodec: JsonValueCodec[A]) {
    def imap[B](f: A => B)(g: B => A)(defaultValue: B): JsonValueCodec[B] = new JsonValueCodec[B] {
      override def decodeValue(in: JsonReader, default: B): B = f(valueCodec.decodeValue(in, valueCodec.nullValue))
      override def encodeValue(x: B, out: JsonWriter): Unit = valueCodec.encodeValue(g(x), out)
      override def nullValue: B = defaultValue
    }
  }
}
