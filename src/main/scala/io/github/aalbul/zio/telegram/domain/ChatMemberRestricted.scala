package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
case class ChatMemberRestricted(
  user: User,
  isMember: Boolean,
  canChangeInfo: Boolean,
  canInviteUsers: Boolean,
  canPinMembers: Boolean,
  canSendMessages: Boolean,
  canSendMediaMessages: Boolean,
  canSendPolls: Boolean,
  canSendOtherMessages: Boolean,
  canAddWebPagePreviews: Boolean,
  untilDate: Int
) extends ChatMember
