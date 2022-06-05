package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.domain.User.UserOps
import com.pengrad.telegrambot.model.ChatMember as LibChatMember

object ChatMember {
  implicit class ChatMemberOps(member: LibChatMember) {
    def asScala: ChatMember = member.status() match {
      case LibChatMember.Status.creator =>
        ChatMemberOwner(
          user = member.user().asScala,
          isAnonymous = member.isAnonymous,
          customTitle = Option(member.customTitle())
        )
      case LibChatMember.Status.administrator =>
        ChatMemberAdministrator(
          user = member.user().asScala,
          canBeEdited = member.canBeEdited,
          isAnonymous = member.isAnonymous,
          canManageChat = member.canManageChat,
          canDeleteMessages = member.canManageChat,
          canManageVideoChats = member.canManageVideoChats,
          canRestrictMembers = member.canRestrictMembers,
          canPromoteMembers = member.canPromoteMembers,
          canChangeInfo = member.canChangeInfo,
          canInviteUsers = member.canInviteUsers,
          canPostMessages = Option(member.canPostMessages),
          canEditMessages = Option(member.canEditMessages),
          canPinMessages = Option(member.canPinMessages),
          customerTitle = Option(member.customTitle())
        )
      case LibChatMember.Status.member => ChatMemberMember(user = member.user().asScala)
      case LibChatMember.Status.restricted =>
        ChatMemberRestricted(
          user = member.user().asScala,
          isMember = member.isMember,
          canChangeInfo = member.canChangeInfo,
          canInviteUsers = member.canInviteUsers,
          canPinMembers = member.canPinMessages,
          canSendMessages = member.canSendMessages,
          canSendMediaMessages = member.canSendMediaMessages,
          canSendPolls = member.canSendPolls,
          canSendOtherMessages = member.canSendOtherMessages,
          canAddWebPagePreviews = member.canAddWebPagePreviews,
          untilDate = member.untilDate()
        )
      case LibChatMember.Status.left   => ChatMemberLeft(user = member.user().asScala)
      case LibChatMember.Status.kicked => ChatMemberBanned(user = member.user().asScala, untilDate = member.untilDate())
    }
  }
}

trait ChatMember
