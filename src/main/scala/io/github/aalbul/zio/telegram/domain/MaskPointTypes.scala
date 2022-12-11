package io.github.aalbul.zio.telegram.domain

import io.circe.Decoder
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object MaskPointTypes extends Enumeration {
  implicit val maskPointTypeDecoder: Decoder[MaskPointType] = Decoder.decodeString.map(byName)

  type MaskPointType = Value

  val Forehead, Eyes, Mouth, Chin = Value

  def byName(name: String): MaskPointType = withName(StringOps(name).capitalize)
}
