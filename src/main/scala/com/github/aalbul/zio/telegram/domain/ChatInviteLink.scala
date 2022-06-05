package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.User.UserOps
import com.pengrad.telegrambot.model.ChatInviteLink as LibChatInviteLink

import java.time.Instant

object ChatInviteLink {
  implicit class ChatInviteLinkOps(link: LibChatInviteLink) {
    def asScala: ChatInviteLink = ChatInviteLink(
      inviteLink = link.inviteLink(),
      creator = link.creator().asScala,
      createsJoinRequest = link.createsJoinRequest(),
      isPrimary = link.isPrimary,
      isRevoked = link.isRevoked,
      name = Option(link.name()),
      expirationDate = Option(link.expireDate()).map(date => Instant.ofEpochSecond(date.toLong)),
      memberLimit = Option(link.memberLimit()),
      pendingJoinRequestCount = Option(link.pendingJoinRequestCount())
    )
  }
}

case class ChatInviteLink(
  inviteLink: String,
  creator: User,
  createsJoinRequest: Boolean,
  isPrimary: Boolean,
  isRevoked: Boolean,
  name: Option[String],
  expirationDate: Option[Instant],
  memberLimit: Option[Int],
  pendingJoinRequestCount: Option[Int]
)
