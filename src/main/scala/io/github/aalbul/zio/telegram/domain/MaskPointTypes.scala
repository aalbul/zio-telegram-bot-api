package io.github.aalbul.zio.telegram.domain

object MaskPointTypes extends Enumeration {
  type MastPointType = Value

  val Forehead, Eyes, Mouth, Chin = Value

  def byName(name: String): MastPointType = withName(StringOps(name).capitalize)
}
