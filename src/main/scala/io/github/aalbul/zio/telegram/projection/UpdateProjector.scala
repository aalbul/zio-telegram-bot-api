package io.github.aalbul.zio.telegram.projection

import cats.MonoidK
import cats.syntax.semigroupk.*
import io.github.aalbul.zio.telegram.domain.Update

object UpdateProjector {
  def apply[T: UpdateProjector]: UpdateProjector[T] = implicitly[UpdateProjector[T]]

  implicit val updateProjectorMonoidK: MonoidK[UpdateProjector] = new MonoidK[UpdateProjector] {
    override def empty[A]: UpdateProjector[A] = _ => None

    override def combineK[A](x: UpdateProjector[A], y: UpdateProjector[A]): UpdateProjector[A] =
      (update: Update) => x.project(update).orElse(y.project(update))
  }

  implicit def fromMessageProjector[T](implicit
    messageProjector: MessageProjector[T]
  ): UpdateProjector[T] = (update: Update) => update.message.flatMap(messageProjector.project)

  val all: UpdateProjector[UpdateProjection] =
    Seq(UpdateProjector.fromMessageProjector(MessageProjector.all))
      .reduce(_ <+> _)
}

trait UpdateProjector[+T] {
  def project(update: Update): Option[T]
}
