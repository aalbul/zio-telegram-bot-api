package io.github.aalbul.zio.telegram.engine

import io.circe.Decoder
import io.github.aalbul.zio.telegram.command.Command
import zio.Task
import zio.stream.ZStream

object BotEngine {
  case class BotException(message: String) extends Exception(message)

  case class ApiCommandExecutionException(command: String, error: String)
    extends Exception(s"Failed executing $command api command: $error")
}

trait BotEngine {
  def execute[T: Decoder](command: Command[T]): Task[T]
  def streamFile(path: String): Task[ZStream[Any, Throwable, Byte]]
}
