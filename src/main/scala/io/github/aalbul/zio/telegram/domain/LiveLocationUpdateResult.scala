package io.github.aalbul.zio.telegram.domain

import io.circe.Decoder

object LiveLocationUpdateResult {
  private val decodeBoolean: Decoder[LiveLocationUpdateResult] =
    Decoder.decodeBoolean.map(result => LiveLocationUpdateResult(message = None, inlineUpdated = result))
  private val decodeMessage: Decoder[LiveLocationUpdateResult] =
    implicitly[Decoder[Message]].map(message =>
      LiveLocationUpdateResult(message = Some(message), inlineUpdated = false)
    )

  implicit val decoder: Decoder[LiveLocationUpdateResult] = decodeBoolean.or(decodeMessage)
}

/** If the edited message is not an inline message, the edited `message` is returned, otherwise `inlineUpdated` with
  * True is returned.
  * @param message
  *   \- edited message
  * @param inlineUpdated
  *   \- true when edited message is inline message
  */
case class LiveLocationUpdateResult(message: Option[Message], inlineUpdated: Boolean)
