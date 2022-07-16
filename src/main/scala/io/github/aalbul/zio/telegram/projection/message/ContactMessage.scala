package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.domain.{Contact, Message}
import io.github.aalbul.zio.telegram.projection.MessageProjector
import io.github.aalbul.zio.telegram.projection.message.MessageProjection.Data

object ContactMessage {
  implicit val contactMessageProjector: MessageProjector[ContactMessage] = (message: Message) =>
    for {
      data <- Data.of(message)
      contact <- message.contact
    } yield ContactMessage(
      data = data,
      contact = contact
    )
}

case class ContactMessage(data: Data, contact: Contact) extends MessageProjection
