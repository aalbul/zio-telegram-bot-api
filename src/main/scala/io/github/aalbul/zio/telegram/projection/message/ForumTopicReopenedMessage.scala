package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{ForumTopicReopened, Message}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object ForumTopicReopenedMessage {
  implicit val forumTopicReopenedProjector: MessageProjector[ForumTopicReopenedMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      forumTopicReopened <- message.forumTopicReopened
    } yield ForumTopicReopenedMessage(
      data = data,
      forumTopicReopened = forumTopicReopened
    )
}

case class ForumTopicReopenedMessage(data: Data, forumTopicReopened: ForumTopicReopened)
  extends ServiceMessageProjection
