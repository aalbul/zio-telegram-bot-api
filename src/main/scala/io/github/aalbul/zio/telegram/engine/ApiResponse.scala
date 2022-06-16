package io.github.aalbul.zio.telegram.engine

import cats.syntax.functor.*
import io.circe.Decoder
import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ApiResponse {

  implicit def apiResponseDecoder[T: Decoder]: Decoder[ApiResponse[? <: T]] =
    implicitly[Decoder[FailureApiResponse]].widen or implicitly[Decoder[SuccessApiResponse[T]]].widen
}

sealed trait ApiResponse[T]

@ConfiguredJsonCodec(decodeOnly = true)
case class FailureApiResponse(ok: Boolean, errorCode: Int, description: String) extends ApiResponse[Nothing]

@ConfiguredJsonCodec(decodeOnly = true)
case class SuccessApiResponse[T](result: T) extends ApiResponse[T]
