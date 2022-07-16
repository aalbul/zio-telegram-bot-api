package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Message, Video}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MediaMessageProjection.Media
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object VideoMessage {
  implicit val videoMessageProjector: MessageProjector[VideoMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      video <- message.video
    } yield VideoMessage(
      data = data,
      media = Media.of(message),
      video = video
    )
}

case class VideoMessage(
  data: Data,
  media: Media,
  video: Video
) extends MediaMessageProjection
