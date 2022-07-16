package io.github.aalbul.zio.telegram.projection

import cats.MonoidK
import io.github.aalbul.zio.telegram.domain.Message
import io.github.aalbul.zio.telegram.projection.message.*
import cats.syntax.semigroupk.*

object MessageProjector {
  def apply[T <: MessageProjection: MessageProjector]: MessageProjector[T] = implicitly[MessageProjector[T]]

  implicit val messageProjectorMonoidK: MonoidK[MessageProjector] = new MonoidK[MessageProjector] {
    override def empty[A]: MessageProjector[A] = _ => None

    override def combineK[A](x: MessageProjector[A], y: MessageProjector[A]): MessageProjector[A] =
      (message: Message) => x.project(message).orElse(y.project(message))
  }

  val all: MessageProjector[MessageProjection] = Seq(
    MessageProjector[AnimationMessage],
    MessageProjector[TextMessage],
    MessageProjector[StickerMessage],
    MessageProjector[VideoNoteMessage],
    MessageProjector[VoiceMessage],
    MessageProjector[ContactMessage],
    MessageProjector[LocationMessage],
    MessageProjector[DiceMessage],
    MessageProjector[GameMessage],
    MessageProjector[PollMessage],
    MessageProjector[VenueMessage],
    MediaMessageProjection.all
  ).reduce(_ <+> _)
}

trait MessageProjector[+T] {
  def project(message: Message): Option[T]
}
