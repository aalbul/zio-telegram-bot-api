package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import java.time.Instant

@ConfiguredJsonCodec(decodeOnly = true)
case class ChatJoinRequest(
  chat: Chat,
  from: User,
  date: Instant,
  bio: Option[String],
  inviteLink: Option[ChatInviteLink]
)
