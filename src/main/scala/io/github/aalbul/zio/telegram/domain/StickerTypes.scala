package io.github.aalbul.zio.telegram.domain

import io.circe.Decoder
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.StringOps

object StickerTypes extends Enumeration {
  private lazy val indexed = values
    .map(value => value.toString.camelToSnakeCase -> value)
    .toMap

  def byName(name: String): StickerType = indexed(name)

  implicit val stickerTypeDecoder: Decoder[StickerType] = Decoder.decodeString.map(byName)

  type StickerType = Value

  val Regular, Mask, CustomEmoji = Value
}
