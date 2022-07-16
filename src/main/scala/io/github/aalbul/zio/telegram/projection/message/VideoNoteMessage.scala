package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Message, VideoNote}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object VideoNoteMessage {
  implicit val videoNoteMessageProjector: MessageProjector[VideoNoteMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      videoNote <- message.videoNote
    } yield VideoNoteMessage(
      data = data,
      videoNote = videoNote
    )
}

case class VideoNoteMessage(
  data: Data,
  videoNote: VideoNote
) extends MessageProjection
