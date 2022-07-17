package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.Message
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object ChannelChatCreatedMessage {
  implicit val channelChatCreatedProjector: MessageProjector[ChannelChatCreatedMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      created <- message.channelChatCreated
      if created
    } yield ChannelChatCreatedMessage(data = data)
}

case class ChannelChatCreatedMessage(data: Data) extends ServiceMessageProjection
