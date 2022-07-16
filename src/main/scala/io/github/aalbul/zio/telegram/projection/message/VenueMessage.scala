package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Message, Venue}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object VenueMessage {
  implicit val venueMessageProjector: MessageProjector[VenueMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      venue <- message.venue
    } yield VenueMessage(
      data = data,
      venue = venue
    )
}

case class VenueMessage(data: Data, venue: Venue) extends MessageProjection
