package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

@ConfiguredJsonCodec
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
