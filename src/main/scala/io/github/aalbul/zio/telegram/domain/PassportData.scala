package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object PassportData {
  implicit val passportDataJsonCodec: JsonValueCodec[PassportData] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[PassportData]]
    * @param data
    *   Array with information about documents and other Telegram Passport elements that was shared with the bot
    * @param credentials
    *   Encrypted credentials required to decrypt the data
    * @return
    *   [[PassportData]] builder
    */
  def of(data: Seq[EncryptedPassportElement], credentials: EncryptedCredentials): PassportData =
    PassportData(data = data, credentials = credentials)
}

/** Describes Telegram Passport data shared with the bot by the user.
  */
case class PassportData(data: Seq[EncryptedPassportElement], credentials: EncryptedCredentials)
