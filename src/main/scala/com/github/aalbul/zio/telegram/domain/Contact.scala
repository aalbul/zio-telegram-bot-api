package com.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
case class Contact(
  phoneNumber: String,
  firstName: String,
  lastName: Option[String],
  userId: Option[Long],
  vcard: Option[String]
)
