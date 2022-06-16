package io.github.aalbul.zio.telegram.domain

import io.circe.Decoder
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ChatTypes extends Enumeration {
  implicit val chatTypeDecoder: Decoder[ChatType] = Decoder.decodeString.map(byName)

  type ChatType = Value

  val Sender, Private, Group, Supergroup, Channel = Value

  def byName(name: String): ChatType = withName(StringOps(name).capitalize)
}
