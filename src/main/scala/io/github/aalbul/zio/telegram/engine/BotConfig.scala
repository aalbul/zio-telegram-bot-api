package io.github.aalbul.zio.telegram.engine

import zio.{ULayer, ZLayer}

object BotConfig {
  def layer(token: String): ULayer[BotConfig] = ZLayer.succeed(BotConfig(token))
}

case class BotConfig(token: String)
