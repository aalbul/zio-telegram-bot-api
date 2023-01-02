package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{ForumTopicEdited, Message}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object ForumTopicEditedMessage {
  implicit val forumTopicEditedProjector: MessageProjector[ForumTopicEditedMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      forumTopicEdited <- message.forumTopicEdited
    } yield ForumTopicEditedMessage(
      data = data,
      forumTopicEdited = forumTopicEdited
    )
}

case class ForumTopicEditedMessage(data: Data, forumTopicEdited: ForumTopicEdited) extends ServiceMessageProjection
