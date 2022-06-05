package com.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

import java.time.Instant

@ConfiguredJsonCodec(decodeOnly = true)
case class ChatMemberUpdated(
  chat: Chat,
  from: User,
  date: Instant,
  oldChatMember: ChatMember,
  newChatMember: ChatMember,
  inviteLink: Option[ChatInviteLink]
)
