package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Message, WriteAccessAllowed}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object WriteAccessAllowedMessage {
  implicit val writeAccessAllowedMessageProjector: MessageProjector[WriteAccessAllowedMessage] =
    (message: Message) =>
      for {
        data <- Data.of(message)
        writeAccessAllowed <- message.writeAccessAllowed
      } yield WriteAccessAllowedMessage(
        data = data,
        writeAccessAllowed = writeAccessAllowed
      )
}

case class WriteAccessAllowedMessage(data: Data, writeAccessAllowed: WriteAccessAllowed)
  extends ServiceMessageProjection
