package com.github.aalbul.zio.telegram.domain

import io.circe.Decoder

object PollTypes extends Enumeration {
  implicit val pollTypeDecoder: Decoder[PollType] = Decoder.decodeString.map(byName)

  type PollType = Value

  val Regular, Quiz = Value

  def byName(name: String): PollType = withName(s"${name.headOption.map(_.toUpper).getOrElse("")}${name.drop(1)}")
}
