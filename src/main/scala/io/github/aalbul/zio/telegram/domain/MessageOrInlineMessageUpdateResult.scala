package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.{JsonReader, JsonValueCodec, JsonWriter}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.Message.messageJsonCodec

import scala.util.Try

object MessageOrInlineMessageUpdateResult {
  implicit val messageOrInlineMessageUpdateResultJsonCodec: JsonValueCodec[MessageOrInlineMessageUpdateResult] =
    new JsonValueCodec[MessageOrInlineMessageUpdateResult] {
      override def decodeValue(
        in: JsonReader,
        default: MessageOrInlineMessageUpdateResult
      ): MessageOrInlineMessageUpdateResult =
        Try {
          in.setMark()
          booleanJsonCodec.decodeValue(in, false)
        }.map(result => MessageOrInlineMessageUpdateResult(message = None, inlineUpdated = result))
          .getOrElse {
            in.rollbackToMark()
            MessageOrInlineMessageUpdateResult(
              message = Some(messageJsonCodec.decodeValue(in, null)),
              inlineUpdated = false
            )
          }

      override def encodeValue(x: MessageOrInlineMessageUpdateResult, out: JsonWriter): Unit = x match {
        case MessageOrInlineMessageUpdateResult(Some(message), _) => messageJsonCodec.encodeValue(message, out)
        case _                                                    => booleanJsonCodec.encodeValue(x.inlineUpdated, out)
      }

      override def nullValue: MessageOrInlineMessageUpdateResult = null
    }
}

/** If the edited message is not an inline message, the edited `message` is returned, otherwise `inlineUpdated` with
  * True is returned.
  * @param message
  *   \- edited message
  * @param inlineUpdated
  *   \- true when edited message is inline message
  */
case class MessageOrInlineMessageUpdateResult(message: Option[Message], inlineUpdated: Boolean)
