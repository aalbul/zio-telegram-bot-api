package com.github.aalbul.zio.telegram.domain

object MaskPointTypes extends Enumeration {
  type MastPointType = Value

  val Forehead, Eyes, Mouth, Chin = Value

  def byName(name: String): MastPointType = withName(s"${name.headOption.map(_.toUpper).getOrElse("")}${name.drop(1)}")
}
