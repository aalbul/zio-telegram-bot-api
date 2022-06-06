package com.github.aalbul.zio.telegram

import com.github.aalbul.zio.telegram.domain.command.{CopyMessageRequest, ForwardMessageRequest, GetUpdatesRequest, SendMessageRequest, SendPhotoRequest}
import com.github.aalbul.zio.telegram.domain.{Message, MessageId, Update, User}
import zio.Task
import zio.stream.ZStream

object Bot {
  case class BotConfig(token: String, chunkSize: Int = 100, timeoutSeconds: Int = 10)
}

trait Bot {
  def getMe: Task[User]
  def getUpdates(request: GetUpdatesRequest): Task[Seq[Update]]
  def stream: ZStream[Any, Throwable, Update]
  def sendMessage(request: SendMessageRequest): Task[Message]
  def forwardMessage(request: ForwardMessageRequest): Task[Message]
  def copyMessage(request: CopyMessageRequest): Task[MessageId]
  def sendPhoto(request: SendPhotoRequest): Task[Message]
}
