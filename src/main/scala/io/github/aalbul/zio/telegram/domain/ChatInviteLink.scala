package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import java.time.Instant

@ConfiguredJsonCodec(decodeOnly = true)
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
