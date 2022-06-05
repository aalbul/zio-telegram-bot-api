package com.github.aalbul.zio.telegram.domain

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
