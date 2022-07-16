package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Document, Message}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MediaMessageProjection.Media
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object DocumentMessage {
  implicit val documentMessageProjector: MessageProjector[DocumentMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      document <- message.document
      if Seq(message.animation, message.audio, message.photo, message.video).forall(_.isEmpty)
    } yield DocumentMessage(
      data = data,
      media = Media.of(message),
      document = document
    )
}

case class DocumentMessage(
  data: Data,
  media: Media,
  document: Document
) extends MediaMessageProjection
