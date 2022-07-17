package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Message, SuccessfulPayment}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object SuccessfulPaymentMessage {
  implicit val successfulPaymentProjector: MessageProjector[SuccessfulPaymentMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      payment <- message.successfulPayment
    } yield SuccessfulPaymentMessage(
      data = data,
      payment = payment
    )
}

case class SuccessfulPaymentMessage(data: Data, payment: SuccessfulPayment) extends ServiceMessageProjection
