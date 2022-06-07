package com.github.aalbul.zio.telegram.engine

import com.github.aalbul.zio.telegram.command.Command
import io.circe.Decoder
import zio.Task

object BotEngine {
  case class BotException(message: String) extends Exception(message)

  case class ApiCommandExecutionException(command: String, error: String)
    extends Exception(s"Failed executing $command api command: $error")
}

trait BotEngine {
  def execute[T: Decoder](command: Command[T]): Task[T]
}
