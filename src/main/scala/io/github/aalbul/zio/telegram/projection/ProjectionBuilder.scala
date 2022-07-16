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

  def stream(chunkSize: Long = 100L): ZStream[BotEngine & Bot, Throwable, UpdateProjection] =
    ZStream
      .unwrap(ZIO.serviceWith[Bot](_.streamUpdates(chunkSize)))
      .map(projector.project)
      .collectSome
}
