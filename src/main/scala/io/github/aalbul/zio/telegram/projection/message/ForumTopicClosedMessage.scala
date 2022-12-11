package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{ForumTopicClosed, Message}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object ForumTopicClosedMessage {
  implicit val forumTopicClosedProjector: MessageProjector[ForumTopicClosedMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      forumTopicClosed <- message.forumTopicClosed
    } yield ForumTopicClosedMessage(
      data = data,
      forumTopicClosed = forumTopicClosed
    )
}

case class ForumTopicClosedMessage(data: Data, forumTopicClosed: ForumTopicClosed) extends ServiceMessageProjection
