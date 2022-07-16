package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Location, Message}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object LocationMessage {
  implicit val locationMessageProjector: MessageProjector[LocationMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      location <- message.location
    } yield LocationMessage(
      data = data,
      location = location
    )
}

case class LocationMessage(data: Data, location: Location) extends MessageProjection
