package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.domain.{ChatMemberUpdated, Update, UpdateType}

object UpdatedChatMember {
  implicit val updatedChatMemberProjector: UpdateProjector[UpdatedChatMember] = new UpdateProjector[UpdatedChatMember] {
    override def project(update: Update): Option[UpdatedChatMember] = update.chatMember.map(UpdatedChatMember(_))

    override val updateTypes: Set[UpdateType] = Set(UpdateType.ChatMember)
  }
}

case class UpdatedChatMember(updated: ChatMemberUpdated) extends UpdateProjection
