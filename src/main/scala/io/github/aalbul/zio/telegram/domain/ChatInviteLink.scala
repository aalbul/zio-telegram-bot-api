package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import java.time.Instant

object ChatInviteLink {
  implicit val chatInviteLinkJsonCodec: JsonValueCodec[ChatInviteLink] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
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
