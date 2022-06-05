package com.github.aalbul.zio.telegram.domain

import io.circe.{Decoder, Encoder}

object PollTypes extends Enumeration {
  implicit val pollTypeDecoder: Decoder[PollType] = Decoder.decodeString.map(byName)
  implicit val pollTypeEncoder: Encoder[PollType] = Encoder.encodeString.contramap[PollType](pollType =>
    s"${pollType.toString.headOption.map(_.toLower).getOrElse("")}${pollType.toString.tail}"
  )

  type PollType = Value

  val Regular, Quiz = Value

  def byName(name: String): PollType = withName(s"${name.headOption.map(_.toUpper).getOrElse("")}${name.tail}")
}
