package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Audio, Message}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MediaMessageProjection.Media
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object AudioMessage {
  implicit val audioMessageProjector: MessageProjector[AudioMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      audio <- message.audio
    } yield AudioMessage(
      data = data,
      media = Media.of(message),
      audio = audio
    )
}

case class AudioMessage(data: Data, media: Media, audio: Audio) extends MediaMessageProjection
