package com.github.aalbul.zio.telegram.domain.command

import cats.syntax.functor.*
import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

object ApiResponse {
  private val failureApiResponseDecoder: Decoder[ApiResponse[Nothing]] = deriveDecoder[FailureApiResponse].widen
  private def successDecoder[T: Decoder]: Decoder[ApiResponse[T]] = deriveDecoder[SuccessApiResponse[T]].widen

  implicit def apiResponseDecoder[T: Decoder]: Decoder[ApiResponse[? <: T]] =
    failureApiResponseDecoder.widen or successDecoder[T].widen
}

sealed trait ApiResponse[T]

case class FailureApiResponse(ok: Boolean, errorCode: Int, description: String) extends ApiResponse[Nothing]
case class SuccessApiResponse[T](result: T) extends ApiResponse[T]
