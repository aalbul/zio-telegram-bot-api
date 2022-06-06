package com.github.aalbul.zio.telegram.domain.command

import com.github.aalbul.zio.telegram.domain.User
import io.circe.generic.extras.ConfiguredJsonCodec

@ConfiguredJsonCodec(decodeOnly = true)
case class GetMeResponse(ok: Boolean, errorCode: Option[Int], description: Option[String], result: Option[User])
  extends ApiResponse
