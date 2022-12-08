package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.domain.{PollAnswer, Update, UpdateTypes}
import io.github.aalbul.zio.telegram.domain.UpdateTypes.UpdateType

object NewPollAnswer {
  implicit val newPollAnswerProjector: UpdateProjector[NewPollAnswer] = new UpdateProjector[NewPollAnswer] {
    override def project(update: Update): Option[NewPollAnswer] = update.pollAnswer.map(NewPollAnswer(_))

    override val updateTypes: Set[UpdateType] = Set(UpdateTypes.PollAnswer)
  }
}

case class NewPollAnswer(pollAnswer: PollAnswer) extends UpdateProjection
