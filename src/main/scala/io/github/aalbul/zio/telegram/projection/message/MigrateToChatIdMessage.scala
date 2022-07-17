package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.Message
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object MigrateToChatIdMessage {
  implicit val migrateToChatIdProjector: MessageProjector[MigrateToChatIdMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      chatId <- message.migrateToChatId
    } yield MigrateToChatIdMessage(
      data = data,
      chatId = chatId
    )
}

case class MigrateToChatIdMessage(data: Data, chatId: Long) extends MessageProjection
