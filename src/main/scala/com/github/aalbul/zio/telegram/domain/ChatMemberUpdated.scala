package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.Chat.ChatOps
import com.github.aalbul.zio.telegram.domain.ChatInviteLink.ChatInviteLinkOps
import com.github.aalbul.zio.telegram.domain.ChatMember.ChatMemberOps
import com.github.aalbul.zio.telegram.domain.User.UserOps
import com.pengrad.telegrambot.model.ChatMemberUpdated as LibChatMemberUpdated

import java.time.Instant

object ChatMemberUpdated {
  implicit class ChatMemberUpdatedOps(updated: LibChatMemberUpdated) {
    def asScala: ChatMemberUpdated = ChatMemberUpdated(
      chat = updated.chat().asScala,
      from = updated.from().asScala,
      date = Instant.ofEpochSecond(updated.date().toLong),
      oldChatMember = updated.oldChatMember().asScala,
      newChatMember = updated.newChatMember().asScala,
      inviteLink = Option(updated.inviteLink()).map(_.asScala)
    )
  }
}

case class ChatMemberUpdated(
  chat: Chat,
  from: User,
  date: Instant,
  oldChatMember: ChatMember,
  newChatMember: ChatMember,
  inviteLink: Option[ChatInviteLink]
)
