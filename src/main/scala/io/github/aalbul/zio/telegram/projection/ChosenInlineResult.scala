package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.domain
import io.github.aalbul.zio.telegram.domain.{Update, UpdateType}

object ChosenInlineResult {
  implicit val chosenInlineResultProjector: UpdateProjector[ChosenInlineResult] =
    new UpdateProjector[ChosenInlineResult] {
      override def project(update: Update): Option[ChosenInlineResult] =
        update.chosenInlineResult.map(ChosenInlineResult(_))

      override val updateTypes: Set[UpdateType] = Set(UpdateType.ChosenInlineResult)
    }
}

case class ChosenInlineResult(result: domain.ChosenInlineResult) extends UpdateProjection
