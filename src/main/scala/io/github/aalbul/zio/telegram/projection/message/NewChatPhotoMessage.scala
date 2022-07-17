package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Message, PhotoSize}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object NewChatPhotoMessage {
  implicit val newChatPhotoProjector: MessageProjector[NewChatPhotoMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      newChatPhoto <- message.newChatPhoto
    } yield NewChatPhotoMessage(
      data = data,
      newPhoto = newChatPhoto
    )
}

case class NewChatPhotoMessage(data: Data, newPhoto: Seq[PhotoSize]) extends ServiceMessageProjection
