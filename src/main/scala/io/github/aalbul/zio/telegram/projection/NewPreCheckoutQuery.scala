package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.domain.{PreCheckoutQuery, Update, UpdateType}

object NewPreCheckoutQuery {
  implicit val newPreCheckoutQueryProjector: UpdateProjector[NewPreCheckoutQuery] =
    new UpdateProjector[NewPreCheckoutQuery] {
      override def project(update: Update): Option[NewPreCheckoutQuery] =
        update.preCheckoutQuery.map(NewPreCheckoutQuery(_))

      override val updateTypes: Set[UpdateType] = Set(UpdateType.PreCheckoutQuery)
    }
}

case class NewPreCheckoutQuery(query: PreCheckoutQuery) extends UpdateProjection
