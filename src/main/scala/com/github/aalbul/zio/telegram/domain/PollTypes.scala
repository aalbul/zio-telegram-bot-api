package com.github.aalbul.zio.telegram.domain

object PollTypes extends Enumeration {
  type PollType = Value

  val Regular, Quiz = Value

  def byName(name: String): PollType = withName(s"${name.headOption.map(_.toUpper).getOrElse("")}${name.drop(1)}")
}
