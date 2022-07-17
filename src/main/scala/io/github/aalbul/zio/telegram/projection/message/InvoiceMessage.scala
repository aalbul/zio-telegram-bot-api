package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Invoice, Message}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object InvoiceMessage {
  implicit val invoiceMessageProjector: MessageProjector[InvoiceMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      invoice <- message.invoice
    } yield InvoiceMessage(
      data = data,
      invoice = invoice
    )
}

case class InvoiceMessage(data: Data, invoice: Invoice) extends ServiceMessageProjection
