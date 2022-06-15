package com.github.aalbul.zio.telegram.domain

import io.circe.Decoder

object LiveLocationUpdateResult {
  private val decodeBoolean: Decoder[LiveLocationUpdateResult] =
    Decoder.decodeBoolean.map(result => LiveLocationUpdateResult(message = None, inlineUpdated = Some(result)))
  private val decodeMessage: Decoder[LiveLocationUpdateResult] =
    implicitly[Decoder[Message]].map(message => LiveLocationUpdateResult(message = Some(message), inlineUpdated = None))

  implicit val decoder: Decoder[LiveLocationUpdateResult] = decodeBoolean.or(decodeMessage)
}

case class LiveLocationUpdateResult(message: Option[Message], inlineUpdated: Option[Boolean])
