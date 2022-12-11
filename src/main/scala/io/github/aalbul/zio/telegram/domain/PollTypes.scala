package io.github.aalbul.zio.telegram.domain

import io.circe.{Decoder, Encoder}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object PollTypes extends Enumeration {
  private lazy val indexed = values
    .map(value => value.toString.camelToSnakeCase -> value)
    .toMap
  private lazy val reverseIndex = indexed.toSeq.map(_.swap).toMap

  type PollType = Value

  val Regular, Quiz = Value

  def byName(name: String): PollType = indexed(name)

  implicit val pollTypeDecoder: Decoder[PollType] = Decoder.decodeString.map(byName)
  implicit val pollTypeEncoder: Encoder[PollType] = Encoder.encodeString.contramap(reverseIndex)
}
