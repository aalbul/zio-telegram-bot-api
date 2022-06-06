package com.github.aalbul.zio.telegram.domain.command

import com.github.aalbul.zio.telegram.domain.MessageId
import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
case class CopyMessageResponse(
  ok: Boolean,
  errorCode: Option[Int],
  description: Option[String],
  result: Option[MessageId]
) extends ApiResponse
