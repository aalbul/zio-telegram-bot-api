package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Message, User}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object LeftChatMemberMessage {
  implicit val leftChatMemberMessageProjector: MessageProjector[LeftChatMemberMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      leftChatMember <- message.leftChatMember
    } yield LeftChatMemberMessage(
      data = data,
      member = leftChatMember
    )
}

case class LeftChatMemberMessage(data: Data, member: User) extends ServiceMessageProjection
