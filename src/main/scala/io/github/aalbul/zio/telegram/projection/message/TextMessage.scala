package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.MessageEntityTypes.{BotCommand, MessageEntityType}
import io.github.aalbul.zio.telegram.domain.{Message, MessageEntity}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object TextMessage {
  implicit val textMessageProjector: MessageProjector[TextMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      text <- message.text
    } yield TextMessage(
      data = data,
      entities = message.entities.toSeq.flatten,
      text = text
    )
}

case class TextMessage(
  data: Data,
  entities: Seq[MessageEntity],
  text: String
) extends MessageProjection {
  private lazy val messageEntityTypes: Set[MessageEntityType] = entities.map(_.`type`).toSet

  def hasEntity(entity: MessageEntityType): Boolean = messageEntityTypes.contains(entity)
  lazy val isBotCommand: Boolean = hasEntity(BotCommand)
}
