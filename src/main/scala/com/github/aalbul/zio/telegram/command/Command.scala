package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.engine.BotEngine
import io.circe.Decoder
import zio.{RIO, ZIO}

abstract class Command[Result : Decoder] {
  val name: String
  def parameters: ApiParameters
  val execute: RIO[BotEngine, Result] = ZIO.serviceWithZIO[BotEngine](_.execute(this))
}
