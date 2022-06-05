package com.github.aalbul.zio.telegram

import com.github.aalbul.zio.telegram.Bot.BotConfig
import com.github.aalbul.zio.telegram.domain.Update
import com.github.aalbul.zio.telegram.domain.Update.UpdateOps
import com.github.aalbul.zio.telegram.util.Sugar.TelegramBotOps
import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.request.{AbstractSendRequest, GetUpdates}
import com.pengrad.telegrambot.response.{GetUpdatesResponse, SendResponse}
import zio.stream.ZStream
import zio.{Chunk, Task, ZIO}

class JavaApiPoweredBot(config: BotConfig) extends Bot {
  private val internalBot = new TelegramBot.Builder(config.token).build()

  def stream: ZStream[Any, Throwable, Update] =
    ZStream
      .unfoldChunkZIO(0) { offset =>
        for {
          response <- internalBot.executeRequest[GetUpdates, GetUpdatesResponse](
            new GetUpdates().offset(offset).limit(config.chunkSize).timeout(config.timeoutSeconds)
          )
          updates = Chunk.fromJavaIterable(response.updates())
        } yield Some(updates, updates.lastOption.map(_.updateId() + 1).getOrElse(offset))
      }
      .map(_.asScala)

  def send[T <: AbstractSendRequest[T]](request: T): Task[Unit] =
    internalBot.executeRequest[T, SendResponse](request).unit

  def shutdown: Task[Unit] = ZIO.attempt(internalBot.shutdown())
}
