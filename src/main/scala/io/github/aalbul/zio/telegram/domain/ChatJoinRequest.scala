package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import java.time.Instant

object ChatJoinRequest {
  implicit val chatJoinRequestJsonObject: JsonValueCodec[ChatJoinRequest] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

case class ChatJoinRequest(
  chat: Chat,
  from: User,
  date: Instant,
  bio: Option[String],
  inviteLink: Option[ChatInviteLink]
)
