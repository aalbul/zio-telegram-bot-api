package com.github.aalbul.zio.telegram.domain.command

import com.github.aalbul.zio.telegram.domain.{ApiResponse, Message}
import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
case class SendMessageResponse(
  ok: Boolean,
  errorCode: Option[Int],
  description: Option[String],
  result: Option[Message]
) extends ApiResponse
