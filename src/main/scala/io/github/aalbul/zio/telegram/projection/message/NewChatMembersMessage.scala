package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Message, User}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object NewChatMembersMessage {
  implicit val newChatMembersProjector: MessageProjector[NewChatMembersMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      newChatMembers <- message.newChatMembers
    } yield NewChatMembersMessage(
      data = data,
      newMembers = newChatMembers
    )
}

case class NewChatMembersMessage(data: Data, newMembers: Seq[User]) extends ServiceMessageProjection
