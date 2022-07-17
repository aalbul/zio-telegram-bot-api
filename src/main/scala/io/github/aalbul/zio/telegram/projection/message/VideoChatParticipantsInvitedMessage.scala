package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Message, VideoChatParticipantsInvited}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object VideoChatParticipantsInvitedMessage {
  implicit val videoChatParticipantsInvitedProjector: MessageProjector[VideoChatParticipantsInvitedMessage] =
    (message: Message) =>
      for {
        data <- Data.of(message)
        event <- message.videoChatParticipantsInvited
      } yield VideoChatParticipantsInvitedMessage(
        data = data,
        event = event
      )
}

case class VideoChatParticipantsInvitedMessage(data: Data, event: VideoChatParticipantsInvited)
  extends ServiceMessageProjection
