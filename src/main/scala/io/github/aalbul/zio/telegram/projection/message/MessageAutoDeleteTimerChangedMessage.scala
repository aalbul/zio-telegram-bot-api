package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Message, MessageAutoDeleteTimerChanged}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object MessageAutoDeleteTimerChangedMessage {
  implicit val messageAutoDeleteTimerChangedProjector: MessageProjector[MessageAutoDeleteTimerChangedMessage] =
    (message: Message) =>
      for {
        data <- Data.of(message)
        change <- message.messageAutoDeleteTimerChanged
      } yield MessageAutoDeleteTimerChangedMessage(
        data = data,
        change = change
      )
}

case class MessageAutoDeleteTimerChangedMessage(data: Data, change: MessageAutoDeleteTimerChanged)
  extends ServiceMessageProjection
