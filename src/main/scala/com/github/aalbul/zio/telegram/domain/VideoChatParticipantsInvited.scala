package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.User.UserOps
import com.pengrad.telegrambot.model.VideoChatParticipantsInvited as LibVideoChatParticipantsInvited

import scala.jdk.CollectionConverters.*

object VideoChatParticipantsInvited {
  implicit class VideoChatParticipantsInvitedOps(invited: LibVideoChatParticipantsInvited) {
    def asScala: VideoChatParticipantsInvited =
      VideoChatParticipantsInvited(users = invited.users().asScala.map(_.asScala).toList)
  }
}

case class VideoChatParticipantsInvited(users: Seq[User])
