package com.github.aalbul.zio.telegram.domain.command

import cats.syntax.functor.*
import io.circe.Decoder
import io.circe.generic.extras.ConfiguredJsonCodec

object ApiResponse {

  implicit def apiResponseDecoder[T: Decoder]: Decoder[ApiResponse[? <: T]] =
    implicitly[Decoder[FailureApiResponse]].widen or implicitly[Decoder[SuccessApiResponse[T]]].widen
}

sealed trait ApiResponse[T]

@ConfiguredJsonCodec(decodeOnly = true)
case class FailureApiResponse(ok: Boolean, errorCode: Int, description: String) extends ApiResponse[Nothing]

@ConfiguredJsonCodec(decodeOnly = true)
case class SuccessApiResponse[T](result: T) extends ApiResponse[T]
