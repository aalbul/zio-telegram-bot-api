package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Message, MessageEntity}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MediaMessageProjection.Media
import cats.syntax.semigroupk.*

object MediaMessageProjection {
  object Media {
    def of(message: Message): Media = Media(
      groupId = message.mediaGroupId,
      caption = message.caption,
      captionEntities = message.captionEntities
    )
  }

  case class Media(groupId: Option[String], caption: Option[String], captionEntities: Option[Seq[MessageEntity]])

  val all: MessageProjector[MessageProjection] = Seq(
    MessageProjector[DocumentMessage],
    MessageProjector[VideoMessage],
    MessageProjector[PhotoMessage],
    MessageProjector[AudioMessage]
  ).reduce(_ <+> _)
}

trait MediaMessageProjection extends MessageProjection {
  val media: Media
}
