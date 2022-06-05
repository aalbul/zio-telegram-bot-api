package com.github.aalbul.zio.telegram.domain

import com.pengrad.telegrambot.model.Contact as LibContact

object Contact {
  implicit class ContactOps(contact: LibContact) {
    def asScala: Contact = Contact(
      phoneNumber = contact.phoneNumber(),
      firstName = contact.firstName(),
      lastName = Option(contact.lastName()),
      userId = Option(contact.userId()),
      vcard = Option(contact.vcard())
    )
  }
}

case class Contact(
  phoneNumber: String,
  firstName: String,
  lastName: Option[String],
  userId: Option[Long],
  vcard: Option[String]
)
