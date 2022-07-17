package io.github.aalbul.zio.telegram.projection.message

import cats.syntax.semigroupk.*
import io.github.aalbul.zio.telegram.projection.MessageProjector

object ServiceMessageProjection {
  val all: MessageProjector[MessageProjection] = Seq(
    MessageProjector[NewChatMembersMessage],
    MessageProjector[LeftChatMemberMessage],
    MessageProjector[NewChatTitleMessage],
    MessageProjector[NewChatPhotoMessage],
    MessageProjector[DeleteChatPhotoMessage],
    MessageProjector[GroupChatCreatedMessage]
  ).reduce(_ <+> _)
}

trait ServiceMessageProjection extends MessageProjection
