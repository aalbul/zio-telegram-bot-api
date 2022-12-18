package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.domain.{Update, UpdateType}
import io.github.aalbul.zio.telegram.projection.message.MessageProjection

object EditedMessage {
  implicit val editedMessageProjector: UpdateProjector[EditedMessage] = new UpdateProjector[EditedMessage] {
    override def project(update: Update): Option[EditedMessage] = for {
      editedMessage <- update.editedMessage
      message <- MessageProjector.all.project(editedMessage)
    } yield EditedMessage(message)

    override val updateTypes: Set[UpdateType] = Set(UpdateType.EditedMessage)
  }
}

case class EditedMessage(message: MessageProjection) extends UpdateProjection
