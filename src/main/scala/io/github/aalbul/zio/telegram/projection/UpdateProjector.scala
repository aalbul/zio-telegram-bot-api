package io.github.aalbul.zio.telegram.projection

import cats.MonoidK
import cats.syntax.semigroupk.*
import io.github.aalbul.zio.telegram.domain.UpdateTypes.UpdateType
import io.github.aalbul.zio.telegram.domain.{Update, UpdateTypes}

object UpdateProjector {
  def apply[T: UpdateProjector]: UpdateProjector[T] = implicitly[UpdateProjector[T]]

  implicit val updateProjectorMonoidK: MonoidK[UpdateProjector] = new MonoidK[UpdateProjector] {
    override def empty[A]: UpdateProjector[A] = new UpdateProjector[A] {
      override def project(update: Update): Option[A] = None
      override val updateTypes: Set[UpdateType] = Set.empty
    }

    override def combineK[A](x: UpdateProjector[A], y: UpdateProjector[A]): UpdateProjector[A] = {
      new UpdateProjector[A] {
        override def project(update: Update): Option[A] = x.project(update).orElse(y.project(update))
        override val updateTypes: Set[UpdateType] = x.updateTypes ++ y.updateTypes
      }
    }
  }

  implicit def fromMessageProjector[T](implicit
    messageProjector: MessageProjector[T]
  ): UpdateProjector[T] = new UpdateProjector[T] {
    override def project(update: Update): Option[T] = update.message.flatMap(messageProjector.project)
    override val updateTypes: Set[UpdateType] = Set(UpdateTypes.Message)
  }

  val all: UpdateProjector[UpdateProjection] = Seq(
    fromMessageProjector(MessageProjector.all),
    UpdateProjector[EditedMessage],
    UpdateProjector[ChannelPost],
    UpdateProjector[EditedChannelPost],
    UpdateProjector[NewInlineQuery],
    UpdateProjector[ChosenInlineResult],
    UpdateProjector[NewCallbackQuery],
    UpdateProjector[NewShippingQuery],
    UpdateProjector[NewPreCheckoutQuery],
    UpdateProjector[NewPoll],
    UpdateProjector[NewPollAnswer],
    UpdateProjector[UpdatedChatMember],
    UpdateProjector[UpdatedMyChatMember]
  ).reduce(_ <+> _)
}

trait UpdateProjector[+T] {
  def project(update: Update): Option[T]
  val updateTypes: Set[UpdateType]
}
