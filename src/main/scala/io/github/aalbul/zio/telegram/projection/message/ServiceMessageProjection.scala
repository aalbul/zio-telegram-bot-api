package io.github.aalbul.zio.telegram.projection.message

import cats.syntax.semigroupk.*
import io.github.aalbul.zio.telegram.projection.MessageProjector

object ServiceMessageProjection {
  val all: MessageProjector[ServiceMessageProjection] = Seq(
    MessageProjector[NewChatMembersMessage],
    MessageProjector[LeftChatMemberMessage],
    MessageProjector[NewChatTitleMessage],
    MessageProjector[NewChatPhotoMessage],
    MessageProjector[DeleteChatPhotoMessage],
    MessageProjector[GroupChatCreatedMessage],
    MessageProjector[SupergroupChatCreatedMessage],
    MessageProjector[ChannelChatCreatedMessage],
    MessageProjector[MessageAutoDeleteTimerChangedMessage],
    MessageProjector[MigrateToChatIdMessage],
    MessageProjector[MigrateFromChatIdMessage],
    MessageProjector[PinnedMessageMessage],
    MessageProjector[InvoiceMessage],
    MessageProjector[SuccessfulPaymentMessage],
    MessageProjector[ProximityAlertTriggeredMessage],
    MessageProjector[VideoChatScheduledMessage],
    MessageProjector[VideoChatStartedMessage],
    MessageProjector[VideoChatEndedMessage],
    MessageProjector[VideoChatParticipantsInvitedMessage],
    MessageProjector[WebAppDataMessage],
    MessageProjector[ForumTopicCreatedMessage],
    MessageProjector[ForumTopicClosedMessage],
    MessageProjector[ForumTopicEditedMessage],
    MessageProjector[ForumTopicReopenedMessage],
    MessageProjector[GeneralForumTopicHiddenMessage],
    MessageProjector[GeneralForumTopicUnhiddenMessage],
    MessageProjector[WriteAccessAllowedMessage]
  ).reduce(_ <+> _)
}

trait ServiceMessageProjection extends MessageProjection
