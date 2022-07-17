package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.Message
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object GroupChatCreatedMessage {
  implicit val groupChatCreatedProjector: MessageProjector[GroupChatCreatedMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      created <- message.groupChatCreated
      if created
    } yield GroupChatCreatedMessage(data = data)
}

case class GroupChatCreatedMessage(data: Data) extends MessageProjection
