package io.github.aalbul.zio.telegram.syntax

import io.circe.Decoder
import io.github.aalbul.zio.telegram.command.Command
import io.github.aalbul.zio.telegram.engine.BotEngine
import zio.{RIO, ZIO}

import scala.language.implicitConversions

trait CommandSyntax {
  implicit class CommandOps[T: Decoder](command: Command[T]) {
    val execute: RIO[BotEngine, T] = ZIO.serviceWithZIO[BotEngine](_.execute(command))
  }

  implicit def commandAsZIO[T: Decoder](command: Command[T]): RIO[BotEngine, T] = command.execute
}
