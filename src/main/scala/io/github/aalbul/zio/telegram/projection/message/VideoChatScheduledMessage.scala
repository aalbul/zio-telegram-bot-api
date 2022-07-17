package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Message, VideoChatScheduled}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object VideoChatScheduledMessage {
  implicit val videoChatScheduledProjector: MessageProjector[VideoChatScheduledMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      event <- message.videoChatScheduled
    } yield VideoChatScheduledMessage(
      data = data,
      event = event
    )
}

case class VideoChatScheduledMessage(data: Data, event: VideoChatScheduled) extends ServiceMessageProjection
