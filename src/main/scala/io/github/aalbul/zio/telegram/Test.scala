package io.github.aalbul.zio.telegram

import io.github.aalbul.zio.telegram.bot.{Bot, DefaultBot}
import io.github.aalbul.zio.telegram.engine.{BotConfig, BotEngine, SttpBasedBotEngine}
import io.github.aalbul.zio.telegram.projection.message.TextMessage
import io.github.aalbul.zio.telegram.syntax.all.*
import zio.{Scope, TaskLayer, ULayer, ZIO, ZIOAppArgs, ZIOAppDefault}

object Test extends ZIOAppDefault {
  private val configLayer: ULayer[BotConfig] = BotConfig.layer("2015690116:AAE8oOyfpNWc_w1j3uBFMY3RY4_GV3ewuH0")
  private val botLayer: ULayer[Bot] = DefaultBot.layer
  private val botEngineLayer: TaskLayer[BotEngine] = configLayer >>> SttpBasedBotEngine.layer

  private val layers: TaskLayer[Bot & BotEngine] = botLayer ++ botEngineLayer

  override def run: ZIO[Any & ZIOAppArgs & Scope, Any, Any] = {
    (for {
      bot <- ZIO.service[Bot]
      _ <- bot.project.all.stream().runForeach { case message: TextMessage =>
        for {
          _ <- bot.sendMessage(message.data.chat.id.toString, message.text.reverse)
          _ <- ZIO.attempt(println(message))
        } yield ()
      }
    } yield ()).provideLayer(layers)
  }
}
