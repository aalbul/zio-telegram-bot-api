package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.Message
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object SupergroupChatCreatedMessage {
  implicit val supergroupChatCreatedProjector: MessageProjector[SupergroupChatCreatedMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      created <- message.supergroupChatCreated
      if created
    } yield SupergroupChatCreatedMessage(data = data)
}

case class SupergroupChatCreatedMessage(data: Data) extends ServiceMessageProjection
