package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Message, VideoChatEnded}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object VideoChatEndedMessage {
  implicit val videoChatEndedProjector: MessageProjector[VideoChatEndedMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      event <- message.videoChatEnded
    } yield VideoChatEndedMessage(
      data = data,
      event = event
    )
}

case class VideoChatEndedMessage(data: Data, event: VideoChatEnded) extends ServiceMessageProjection
