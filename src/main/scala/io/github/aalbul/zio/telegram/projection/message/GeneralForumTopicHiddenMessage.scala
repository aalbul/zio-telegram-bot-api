package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{GeneralForumTopicHidden, Message}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object GeneralForumTopicHiddenMessage {
  implicit val generalForumTopicHiddenMessageProjector: MessageProjector[GeneralForumTopicHiddenMessage] =
    (message: Message) =>
      for {
        data <- Data.of(message)
        generalForumTopicHidden <- message.generalForumTopicHidden
      } yield GeneralForumTopicHiddenMessage(
        data = data,
        generalForumTopicHidden = generalForumTopicHidden
      )
}

case class GeneralForumTopicHiddenMessage(data: Data, generalForumTopicHidden: GeneralForumTopicHidden)
  extends ServiceMessageProjection
