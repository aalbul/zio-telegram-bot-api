package io.github.aalbul.zio.telegram.projection

import cats.MonoidK
import cats.syntax.semigroupk.*
import io.github.aalbul.zio.telegram.bot.Bot
import io.github.aalbul.zio.telegram.engine.BotEngine
import io.github.aalbul.zio.telegram.projection.message.*
import zio.ZIO
import zio.stream.ZStream

object ProjectionBuilder {
  def empty: ProjectionBuilder = new ProjectionBuilder(MonoidK[UpdateProjector].empty)
}

class ProjectionBuilder(projector: UpdateProjector[UpdateProjection]) {
  private def plus(otherProjector: UpdateProjector[UpdateProjection]): ProjectionBuilder = new ProjectionBuilder(
    otherProjector <+> projector
  )

  def all: ProjectionBuilder = plus(UpdateProjector.all)
  def withAnimationMessage: ProjectionBuilder = plus(UpdateProjector[AnimationMessage])
  def withAudioMessage: ProjectionBuilder = plus(UpdateProjector[AudioMessage])
  def withContactMessage: ProjectionBuilder = plus(UpdateProjector[ContactMessage])
  def withDiceMessage: ProjectionBuilder = plus(UpdateProjector[DiceMessage])
  def withDocumentMessage: ProjectionBuilder = plus(UpdateProjector[DocumentMessage])
  def withGameMessage: ProjectionBuilder = plus(UpdateProjector[GameMessage])
  def withLocationMessage: ProjectionBuilder = plus(UpdateProjector[LocationMessage])
  def withPhotoMessage: ProjectionBuilder = plus(UpdateProjector[PhotoMessage])
  def withPollMessage: ProjectionBuilder = plus(UpdateProjector[PollMessage])
  def withStickerMessage: ProjectionBuilder = plus(UpdateProjector[StickerMessage])
  def withTextMessage: ProjectionBuilder = plus(UpdateProjector[TextMessage])
  def withVenueMessage: ProjectionBuilder = plus(UpdateProjector[VenueMessage])
  def withVideoMessage: ProjectionBuilder = plus(UpdateProjector[VideoMessage])
  def withVideoNoteMessage: ProjectionBuilder = plus(UpdateProjector[VideoNoteMessage])
  def withVoiceMessage: ProjectionBuilder = plus(UpdateProjector[VoiceMessage])
  def withNewChatMembersMessage: ProjectionBuilder = plus(UpdateProjector[NewChatMembersMessage])
  def withLeftChatMemberMessage: ProjectionBuilder = plus(UpdateProjector[LeftChatMemberMessage])
  def withNewChatTitleMessage: ProjectionBuilder = plus(UpdateProjector[NewChatTitleMessage])
  def withNewChatPhotoMessage: ProjectionBuilder = plus(UpdateProjector[NewChatPhotoMessage])
  def withDeleteChatPhotoMessage: ProjectionBuilder = plus(UpdateProjector[DeleteChatPhotoMessage])
  def withGroupChatCreatedMessage: ProjectionBuilder = plus(UpdateProjector[GroupChatCreatedMessage])
  def withSupergroupChatCreatedMessage: ProjectionBuilder = plus(UpdateProjector[SupergroupChatCreatedMessage])
  def withChannelChatCreatedMessage: ProjectionBuilder = plus(UpdateProjector[ChannelChatCreatedMessage])
  def withMessageAutoDeleteTimerChangedMessage: ProjectionBuilder =
    plus(UpdateProjector[MessageAutoDeleteTimerChangedMessage])
  def withMigrateToChatIdMessage: ProjectionBuilder = plus(UpdateProjector[MigrateToChatIdMessage])
  def withMigrateFromChatIdMessage: ProjectionBuilder = plus(UpdateProjector[MigrateFromChatIdMessage])
  def withPinnedMessageMessage: ProjectionBuilder = plus(UpdateProjector[PinnedMessageMessage])
  def withInvoiceMessage: ProjectionBuilder = plus(UpdateProjector[InvoiceMessage])
  def withSuccessfulPaymentMessage: ProjectionBuilder = plus(UpdateProjector[SuccessfulPaymentMessage])
  def withPassportDataMessage: ProjectionBuilder = plus(UpdateProjector[PassportDataMessage])
  def withProximityAlertTriggeredMessage: ProjectionBuilder = plus(UpdateProjector[ProximityAlertTriggeredMessage])
  def withVideoChatScheduledMessage: ProjectionBuilder = plus(UpdateProjector[VideoChatScheduledMessage])
  def withVideoChatStartedMessage: ProjectionBuilder = plus(UpdateProjector[VideoChatStartedMessage])
  def withVideoChatEndedMessage: ProjectionBuilder = plus(UpdateProjector[VideoChatEndedMessage])
  def withVideoChatParticipantsInvitedMessage: ProjectionBuilder = plus(
    UpdateProjector[VideoChatParticipantsInvitedMessage]
  )
  def withWebAppDataMessage: ProjectionBuilder = plus(UpdateProjector[WebAppDataMessage])
  def withEditedMessage: ProjectionBuilder = plus(UpdateProjector[EditedMessage])
  def withChannelPost: ProjectionBuilder = plus(UpdateProjector[ChannelPost])
  def withEditedChannelPost: ProjectionBuilder = plus(UpdateProjector[EditedChannelPost])
  def withNewInlineQuery: ProjectionBuilder = plus(UpdateProjector[NewInlineQuery])
  def withChosenInlineResult: ProjectionBuilder = plus(UpdateProjector[ChosenInlineResult])
  def withNewCallbackQuery: ProjectionBuilder = plus(UpdateProjector[NewCallbackQuery])
  def withNewShippingQuery: ProjectionBuilder = plus(UpdateProjector[NewShippingQuery])
  def withNewPreCheckoutQuery: ProjectionBuilder = plus(UpdateProjector[NewPreCheckoutQuery])
  def withNewPoll: ProjectionBuilder = plus(UpdateProjector[NewPoll])

  def stream(chunkSize: Long = 100L): ZStream[BotEngine & Bot, Throwable, UpdateProjection] =
    ZStream
      .unwrap(ZIO.serviceWith[Bot](_.streamUpdates(chunkSize = chunkSize, allowedTypes = projector.updateTypes)))
      .map(projector.project)
      .collectSome
}
