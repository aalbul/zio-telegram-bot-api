package com.github.aalbul.zio.telegram

import com.github.aalbul.zio.telegram.domain.Update
import com.pengrad.telegrambot.request.{AbstractSendRequest, SendMessage}
import zio.stream.ZStream
import zio.{Scope, Task, URLayer, ZIO, ZLayer}

object Bot {
  case class BotConfig(token: String, chunkSize: Int = 100, timeoutSeconds: Int = 180)

  def forConfig(config: BotConfig): URLayer[Scope, Bot] =
    ZLayer.fromZIO(ZIO.acquireRelease(ZIO.succeed(new JavaApiPoweredBot(config)))(_.shutdown.orDie))
}

trait Bot {
  def stream: ZStream[Any, Throwable, Update]
  def send[T <: AbstractSendRequest[T]](request: T): Task[Unit]
  def sendMessageWith(chatId: Long, message: String)(customizer: SendMessage => SendMessage): Task[Unit] = send(
    customizer(new SendMessage(chatId, message))
  )
  def sendMessage(chatId: Long, message: String): Task[Unit] = sendMessageWith(chatId, message)(identity)
  def shutdown: Task[Unit]
}
