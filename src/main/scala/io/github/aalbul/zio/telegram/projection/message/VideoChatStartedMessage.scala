package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.Message
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object VideoChatStartedMessage {
  implicit val videoChatStartedProjector: MessageProjector[VideoChatStartedMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      _ <- message.videoChatStarted
    } yield VideoChatStartedMessage(data = data)
}

case class VideoChatStartedMessage(data: Data) extends ServiceMessageProjection
