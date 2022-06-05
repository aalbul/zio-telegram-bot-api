package com.github.aalbul.zio.telegram.domain

import com.pengrad.telegrambot.model.ChatPermissions as LibChatPermissions

object ChatPermissions {
  implicit class ChatPermissionsOps(permissions: LibChatPermissions) {
    def asScala: ChatPermissions = ChatPermissions(
      canSendMessages = Option(permissions.canSendMessages),
      canSendMediaMessages = Option(permissions.canSendMediaMessages),
      canSendPolls = Option(permissions.canSendPolls),
      canSendOtherMessages = Option(permissions.canSendOtherMessages),
      canAddWebPagePreviews = Option(permissions.canAddWebPagePreviews),
      canChangeInfo = Option(permissions.canChangeInfo),
      canInviteUsers = Option(permissions.canInviteUsers),
      canPinMessages = Option(permissions.canPinMessages)
    )
  }
}

case class ChatPermissions(
  canSendMessages: Option[Boolean],
  canSendMediaMessages: Option[Boolean],
  canSendPolls: Option[Boolean],
  canSendOtherMessages: Option[Boolean],
  canAddWebPagePreviews: Option[Boolean],
  canChangeInfo: Option[Boolean],
  canInviteUsers: Option[Boolean],
  canPinMessages: Option[Boolean]
)
