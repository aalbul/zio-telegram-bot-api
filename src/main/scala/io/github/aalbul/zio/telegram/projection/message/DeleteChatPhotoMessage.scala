package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.Message
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object DeleteChatPhotoMessage {
  implicit val deleteChatPhotoProjector: MessageProjector[DeleteChatPhotoMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      delete <- message.deleteChatPhoto
      if delete
    } yield DeleteChatPhotoMessage(data = data)
}

case class DeleteChatPhotoMessage(data: Data) extends ServiceMessageProjection
