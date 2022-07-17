package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.Message
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object MigrateFromChatIdMessage {
  implicit val migrateFromChatIdProjector: MessageProjector[MigrateFromChatIdMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      chatId <- message.migrateFromChatId
    } yield MigrateFromChatIdMessage(
      data = data,
      chatId = chatId
    )
}

case class MigrateFromChatIdMessage(data: Data, chatId: Long) extends MessageProjection
