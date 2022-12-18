package io.github.aalbul.zio.telegram.syntax

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import io.github.aalbul.zio.telegram.command.Command
import io.github.aalbul.zio.telegram.engine.BotEngine
import zio.{RIO, ZIO}

import scala.language.implicitConversions

trait CommandSyntax {
  implicit class CommandOps[T: JsonValueCodec](command: Command[T]) {
    val execute: RIO[BotEngine, T] = ZIO.serviceWithZIO[BotEngine](_.execute(command))
  }

  implicit def commandAsZIO[T: JsonValueCodec](command: Command[T]): RIO[BotEngine, T] = command.execute
}
