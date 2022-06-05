package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.Chat.ChatOps
import com.github.aalbul.zio.telegram.domain.ChatInviteLink.ChatInviteLinkOps
import com.github.aalbul.zio.telegram.domain.User.UserOps
import com.pengrad.telegrambot.model.ChatJoinRequest as LibChatJoinRequest

import java.time.Instant

object ChatJoinRequest {
  implicit class ChatJoinRequestOps(request: LibChatJoinRequest) {
    def asScala: ChatJoinRequest = ChatJoinRequest(
      chat = request.chat().asScala,
      from = request.from().asScala,
      date = Instant.ofEpochSecond(request.date().toLong),
      bio = Option(request.bio()),
      inviteLink = Option(request.inviteLink()).map(_.asScala)
    )
  }
}

case class ChatJoinRequest(
  chat: Chat,
  from: User,
  date: Instant,
  bio: Option[String],
  inviteLink: Option[ChatInviteLink]
)
