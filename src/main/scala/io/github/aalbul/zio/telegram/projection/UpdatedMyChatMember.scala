package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.domain.UpdateTypes.UpdateType
import io.github.aalbul.zio.telegram.domain.{ChatMemberUpdated, Update, UpdateTypes}

object UpdatedMyChatMember {
  implicit val updatedMyChatMemberProjector: UpdateProjector[UpdatedMyChatMember] =
    new UpdateProjector[UpdatedMyChatMember] {
      override def project(update: Update): Option[UpdatedMyChatMember] = update.myChatMember.map(UpdatedMyChatMember(_))

      override val updateTypes: Set[UpdateType] = Set(UpdateTypes.MyChatMember)
    }
}

case class UpdatedMyChatMember(updated: ChatMemberUpdated) extends UpdateProjection
