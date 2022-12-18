package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.{JsonReader, JsonValueCodec, JsonWriter}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.codecs
import io.github.aalbul.zio.telegram.domain.Message.messageJsonCodec

import scala.util.Try

object LiveLocationUpdateResult {
  implicit val liveLocationUpdateResultJsonCodec: JsonValueCodec[LiveLocationUpdateResult] =
    new JsonValueCodec[LiveLocationUpdateResult] {
      override def decodeValue(in: JsonReader, default: LiveLocationUpdateResult): LiveLocationUpdateResult =
        Try {
          in.setMark()
          codecs.boolean.decodeValue(in, false)
        }.map(result => LiveLocationUpdateResult(message = None, inlineUpdated = result))
          .getOrElse {
            in.rollbackToMark()
            LiveLocationUpdateResult(
              message = Some(messageJsonCodec.decodeValue(in, null)),
              inlineUpdated = false
            )
          }

      override def encodeValue(x: LiveLocationUpdateResult, out: JsonWriter): Unit = x match {
        case LiveLocationUpdateResult(Some(message), _) => messageJsonCodec.encodeValue(message, out)
        case _                                          => codecs.boolean.encodeValue(x.inlineUpdated, out)
      }

      override def nullValue: LiveLocationUpdateResult = null
    }
}

/** If the edited message is not an inline message, the edited `message` is returned, otherwise `inlineUpdated` with
  * True is returned.
  * @param message
  *   \- edited message
  * @param inlineUpdated
  *   \- true when edited message is inline message
  */
case class LiveLocationUpdateResult(message: Option[Message], inlineUpdated: Boolean)
