package io.github.aalbul.zio.telegram.engine

import com.github.plokhotnyuk.jsoniter_scala.core.{JsonReader, JsonValueCodec, JsonWriter}
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import scala.util.Try

object ApiResponse {
  private val failureApiResponseJsonCodec: JsonValueCodec[FailureApiResponse] =
    JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))

  implicit def apiResponseJsonCodec[T: JsonValueCodec]: JsonValueCodec[ApiResponse[T]] =
    new JsonValueCodec[ApiResponse[T]] {
      val successfulCodec: JsonValueCodec[SuccessApiResponse[T]] = JsonCodecMaker.make(
        CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
      )

      override def decodeValue(in: JsonReader, default: ApiResponse[T]): ApiResponse[T] =
        Try {
          in.setMark()
          failureApiResponseJsonCodec.decodeValue(in, null)
        }.getOrElse {
          in.rollbackToMark()
          successfulCodec.decodeValue(in, null)
        }

      override def encodeValue(x: ApiResponse[T], out: JsonWriter): Unit = x match {
        case fail: FailureApiResponse        => failureApiResponseJsonCodec.encodeValue(fail, out)
        case response: SuccessApiResponse[T] => successfulCodec.encodeValue(response, out)
      }

      override def nullValue: ApiResponse[T] = null
    }
}

sealed trait ApiResponse[+T]
case class FailureApiResponse(ok: Boolean, errorCode: Int, description: String) extends ApiResponse[Nothing]
case class SuccessApiResponse[T](result: T) extends ApiResponse[T]
