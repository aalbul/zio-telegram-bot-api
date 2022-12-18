package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.domain.{Poll, Update, UpdateType}

object NewPoll {
  implicit val newPollProjector: UpdateProjector[NewPoll] = new UpdateProjector[NewPoll] {
    override def project(update: Update): Option[NewPoll] = update.poll.map(NewPoll(_))

    override val updateTypes: Set[UpdateType] = Set(UpdateType.Poll)
  }
}

case class NewPoll(poll: Poll) extends UpdateProjection
