package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Message, ProximityAlertTriggered}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object ProximityAlertTriggeredMessage {
  implicit val proximityAlertTriggeredProjector: MessageProjector[ProximityAlertTriggeredMessage] =
    (message: Message) =>
      for {
        data <- Data.of(message)
        event <- message.proximityAlertTriggered
      } yield ProximityAlertTriggeredMessage(
        data = data,
        event = event
      )
}

case class ProximityAlertTriggeredMessage(data: Data, event: ProximityAlertTriggered) extends ServiceMessageProjection
