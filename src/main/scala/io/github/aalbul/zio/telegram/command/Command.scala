package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec

abstract class Command[T: JsonValueCodec] {
  val name: String
  def parameters: ApiParameters
}
