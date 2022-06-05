package com.github.aalbul.zio.telegram.domain

case class ChatMemberAdministrator(
  user: User,
  canBeEdited: Boolean,
  isAnonymous: Boolean,
  canManageChat: Boolean,
  canDeleteMessages: Boolean,
  canManageVideoChats: Boolean,
  canRestrictMembers: Boolean,
  canPromoteMembers: Boolean,
  canChangeInfo: Boolean,
  canInviteUsers: Boolean,
  canPostMessages: Option[Boolean],
  canEditMessages: Option[Boolean],
  canPinMessages: Option[Boolean],
  customerTitle: Option[String]
) extends ChatMember
