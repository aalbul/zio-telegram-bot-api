package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object VideoChatParticipantsInvited {

  /** Constructs minimal [[VideoChatParticipantsInvited]]
    * @param users
    *   New members that were invited to the video chat
    * @return
    *   [[VideoChatParticipantsInvited]] builder
    */
  def of(users: Seq[User]): VideoChatParticipantsInvited = VideoChatParticipantsInvited(
    users = users
  )
}

/** This object represents a service message about new members invited to a video chat.
  */
@ConfiguredJsonCodec(decodeOnly = true)
case class VideoChatParticipantsInvited(users: Seq[User])
