package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Message, Poll}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object PollMessage {
  implicit val pollMessageProjector: MessageProjector[PollMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      poll <- message.poll
    } yield PollMessage(
      data = data,
      poll = poll
    )
}

case class PollMessage(data: Data, poll: Poll) extends MessageProjection
