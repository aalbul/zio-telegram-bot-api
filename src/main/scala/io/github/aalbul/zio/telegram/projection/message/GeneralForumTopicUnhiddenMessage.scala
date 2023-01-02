package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{GeneralForumTopicUnhidden, Message}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object GeneralForumTopicUnhiddenMessage {
  implicit val generalForumTopicUnhiddenMessageProjector: MessageProjector[GeneralForumTopicUnhiddenMessage] =
    (message: Message) =>
      for {
        data <- Data.of(message)
        generalForumTopicUnhidden <- message.generalForumTopicUnhidden
      } yield GeneralForumTopicUnhiddenMessage(
        data = data,
        generalForumTopicUnhidden = generalForumTopicUnhidden
      )
}

case class GeneralForumTopicUnhiddenMessage(data: Data, generalForumTopicUnhidden: GeneralForumTopicUnhidden)
  extends ServiceMessageProjection
