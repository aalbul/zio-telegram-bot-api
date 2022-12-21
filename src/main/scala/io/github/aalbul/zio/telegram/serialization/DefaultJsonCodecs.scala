package io.github.aalbul.zio.telegram.serialization

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.JsonCodecMaker
import io.github.aalbul.zio.telegram.serialization.InvariantCodec.JsonValueCodecOps

import java.time.Instant
import java.util.concurrent.TimeUnit
import scala.concurrent.duration.Duration

trait DefaultJsonCodecs {
  implicit val stringJsonCodec: JsonValueCodec[String] = JsonCodecMaker.make
  implicit val longJsonCodec: JsonValueCodec[Long] = JsonCodecMaker.make
  implicit val booleanJsonCodec: JsonValueCodec[Boolean] = JsonCodecMaker.make

  implicit val instantJsonCodec: JsonValueCodec[Instant] =
    longJsonCodec.imap(Instant.ofEpochSecond)(_.getEpochSecond)(null)

  implicit val durationSecondsJsonCodec: JsonValueCodec[Duration] =
    longJsonCodec.imap[Duration](Duration.apply(_, TimeUnit.SECONDS))(_.toSeconds)(null)
}
