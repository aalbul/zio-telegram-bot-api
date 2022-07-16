package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.*
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MediaMessageProjection.Media
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object AnimationMessage {
  implicit val animationMessageProjector: MessageProjector[AnimationMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      animation <- message.animation
      document <- message.document
    } yield AnimationMessage(
      data = data,
      media = Media.of(message),
      document = document,
      animation = animation
    )
}

case class AnimationMessage(
  data: Data,
  media: Media,
  document: Document,
  animation: Animation
) extends MediaMessageProjection
