package com.github.aalbul.zio.telegram.domain

object ChatTypes extends Enumeration {
  type ChatType = Value

  val Sender, Private, Group, Supergroup, Channel = Value

  def byName(name: String): ChatType = withName(s"${name.headOption.map(_.toUpper).getOrElse("")}${name.drop(1)}")
}
