package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Message, Voice}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object VoiceMessage {
  implicit val voiceMessageProjector: MessageProjector[VoiceMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      voice <- message.voice
    } yield VoiceMessage(
      data = data,
      voice = voice
    )
}

case class VoiceMessage(data: Data, voice: Voice) extends MessageProjection
