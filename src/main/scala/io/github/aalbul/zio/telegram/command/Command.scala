package io.github.aalbul.zio.telegram.command

import io.circe.Decoder

abstract class Command[T: Decoder] {
  val name: String
  def parameters: ApiParameters
}
