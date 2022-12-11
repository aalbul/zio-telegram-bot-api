package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{ForumTopicCreated, Message}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object ForumTopicCreatedMessage {
  implicit val forumTopicCreatedProjector: MessageProjector[ForumTopicCreatedMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      forumTopicCreated <- message.forumTopicCreated
    } yield ForumTopicCreatedMessage(
      data = data,
      forumTopicCreated = forumTopicCreated
    )
}

case class ForumTopicCreatedMessage(data: Data, forumTopicCreated: ForumTopicCreated) extends ServiceMessageProjection
