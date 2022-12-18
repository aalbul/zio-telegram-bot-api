package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ChatPermissions {
  implicit val chatPermissionsJsonCodec: JsonValueCodec[ChatPermissions] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

case class ChatPermissions(
  canSendMessages: Option[Boolean],
  canSendMediaMessages: Option[Boolean],
  canSendPolls: Option[Boolean],
  canSendOtherMessages: Option[Boolean],
  canAddWebPagePreviews: Option[Boolean],
  canChangeInfo: Option[Boolean],
  canInviteUsers: Option[Boolean],
  canPinMessages: Option[Boolean],
  canManageTopics: Option[Boolean]
)
