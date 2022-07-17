package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Message, PassportData}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object PassportDataMessage {
  implicit val passportDataProjector: MessageProjector[PassportDataMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      passportData <- message.passportData
    } yield PassportDataMessage(
      data = data,
      passportData = passportData
    )
}

case class PassportDataMessage(data: Data, passportData: PassportData) extends MessageProjection
