package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Message, Sticker}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object StickerMessage {
  implicit val stickerMessageProjector: MessageProjector[StickerMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      sticker <- message.sticker
    } yield StickerMessage(
      data = data,
      sticker = sticker
    )
}

case class StickerMessage(
  data: Data,
  sticker: Sticker
) extends MessageProjection
