package com.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

import java.time.Instant

@ConfiguredJsonCodec(decodeOnly = true)
case class ChatJoinRequest(
  chat: Chat,
  from: User,
  date: Instant,
  bio: Option[String],
  inviteLink: Option[ChatInviteLink]
)
