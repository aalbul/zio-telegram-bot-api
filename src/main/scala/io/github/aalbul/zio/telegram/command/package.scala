package io.github.aalbul.zio.telegram

import io.circe.Decoder
import io.github.aalbul.zio.telegram.engine.BotEngine
import zio.{RIO, ZIO}

package object command {
  implicit class CommandOps[T: Decoder](command: Command[T]) {
    val execute: RIO[BotEngine, T] = ZIO.serviceWithZIO[BotEngine](_.execute(command))
  }
}
