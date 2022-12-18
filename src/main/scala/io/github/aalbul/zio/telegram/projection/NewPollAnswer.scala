package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.domain.{PollAnswer, Update, UpdateType}

object NewPollAnswer {
  implicit val newPollAnswerProjector: UpdateProjector[NewPollAnswer] = new UpdateProjector[NewPollAnswer] {
    override def project(update: Update): Option[NewPollAnswer] = update.pollAnswer.map(NewPollAnswer(_))

    override val updateTypes: Set[UpdateType] = Set(UpdateType.PollAnswer)
  }
}

case class NewPollAnswer(pollAnswer: PollAnswer) extends UpdateProjection
