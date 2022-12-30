package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.SetPassportDataErrors.SetPassportDataErrorsPayload
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.PassportElementError

object SetPassportDataErrors {
  object SetPassportDataErrorsPayload {
    implicit val setPassportDataErrorsPayloadJsonCodec: JsonValueCodec[SetPassportDataErrorsPayload] =
      JsonCodecMaker.make(CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2))
  }

  case class SetPassportDataErrorsPayload(userId: Long, errors: Seq[PassportElementError])

  /** Constructs minimal [[SetPassportDataErrors]] command
    * @param userId
    *   User identifier
    * @param errors
    *   A JSON-serialized array describing the errors
    * @return
    *   [[SetPassportDataErrors]] builder
    */
  def of(userId: Long, errors: Seq[PassportElementError]): SetPassportDataErrors = SetPassportDataErrors(
    SetPassportDataErrorsPayload(userId = userId, errors = errors)
  )
}

/** Informs a user that some of the Telegram Passport elements they provided contains errors. The user will not be able
  * to re-submit their Passport to you until the errors are fixed (the contents of the field for which you returned the
  * error must change). Returns True on success.
  *
  * Use this if the data submitted by the user doesn't satisfy the standards your service requires for any reason. For
  * example, if a birthday date seems invalid, a submitted document is blurry, a scan shows evidence of tampering, etc.
  * Supply some details in the error message to make sure the user knows how to correct the issues.
  */
case class SetPassportDataErrors(payload: SetPassportDataErrorsPayload) extends Command[Boolean] {
  override val name: String = "setPassportDataErrors"
  override def parameters: ApiParameters = JsonBody(payload)
}
