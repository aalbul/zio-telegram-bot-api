package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.Message
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object NewChatTitleMessage {
  implicit val newChatTitleProjector: MessageProjector[NewChatTitleMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      newChatTitle <- message.newChatTitle
    } yield NewChatTitleMessage(
      data = data,
      newTitle = newChatTitle
    )
}

case class NewChatTitleMessage(data: Data, newTitle: String) extends ServiceMessageProjection
