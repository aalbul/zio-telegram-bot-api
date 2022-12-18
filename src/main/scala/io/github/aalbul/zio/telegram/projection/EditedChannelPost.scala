package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.domain.{Update, UpdateType}
import io.github.aalbul.zio.telegram.projection.message.MessageProjection

object EditedChannelPost {
  implicit val editedChannelPostProjector: UpdateProjector[EditedChannelPost] = new UpdateProjector[EditedChannelPost] {
    override def project(update: Update): Option[EditedChannelPost] = for {
      editedChannelPost <- update.editedChannelPost
      message <- MessageProjector.all.project(editedChannelPost)
    } yield EditedChannelPost(message)

    override val updateTypes: Set[UpdateType] = Set(UpdateType.EditedChannelPost)
  }
}

case class EditedChannelPost(message: MessageProjection) extends UpdateProjection
