package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Message, PhotoSize}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MediaMessageProjection.Media
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object PhotoMessage {
  implicit val photoMessageProjector: MessageProjector[PhotoMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      photo <- message.photo
    } yield PhotoMessage(
      data = data,
      media = Media.of(message),
      photo = photo
    )
}

case class PhotoMessage(
  data: Data,
  media: Media,
  photo: Seq[PhotoSize]
) extends MediaMessageProjection
