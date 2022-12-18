package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import java.time.Instant

object ChatMemberUpdated {
  implicit val chatMemberUpdatedJsonCodec: JsonValueCodec[ChatMemberUpdated] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

case class ChatMemberUpdated(
  chat: Chat,
  from: User,
  date: Instant,
  oldChatMember: ChatMember,
  newChatMember: ChatMember,
  inviteLink: Option[ChatInviteLink]
)
