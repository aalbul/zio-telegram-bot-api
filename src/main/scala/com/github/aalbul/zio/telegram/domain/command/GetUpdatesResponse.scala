package com.github.aalbul.zio.telegram.domain.command

import com.github.aalbul.zio.telegram.domain.{ApiResponse, Update}
import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
case class GetUpdatesResponse(
  ok: Boolean,
  errorCode: Option[Int],
  description: Option[String],
  result: Seq[Update]
) extends ApiResponse
