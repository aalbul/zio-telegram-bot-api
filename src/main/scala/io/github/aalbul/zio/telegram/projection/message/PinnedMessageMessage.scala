package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.Message
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data
import io.github.aalbul.zio.telegram.projection.{MessageProjector, UpdateProjection}

object PinnedMessageMessage {
  implicit val pinnedMessageMessageProjector: MessageProjector[PinnedMessageMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      pinnedMessage <- message.pinnedMessage
      message <- MessageProjector.all.project(pinnedMessage)
    } yield PinnedMessageMessage(
      data = data,
      message = message
    )
}

case class PinnedMessageMessage(data: Data, message: UpdateProjection) extends ServiceMessageProjection
