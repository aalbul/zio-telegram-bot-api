package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object EncryptedCredentials {
  implicit val encryptedCredentialsJsonCodec: JsonValueCodec[EncryptedCredentials] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[EncryptedCredentials]] object
    *
    * @param data
    *   Base64-encoded encrypted JSON-serialized data with unique user's payload, data hashes and secrets required for
    *   [[https://core.telegram.org/bots/api#encryptedpassportelement EncryptedPassportElement]] decryption and
    *   authentication
    * @param hash
    *   Base64-encoded data hash for data authentication
    * @param secret
    *   Base64-encoded secret, encrypted with the bot's public RSA key, required for data decryption
    * @return
    *   [[EncryptedCredentials]] builder
    */
  def of(data: String, hash: String, secret: String): EncryptedCredentials = EncryptedCredentials(
    data = data,
    hash = hash,
    secret = secret
  )
}

/** Describes data required for decrypting and authenticating
  * [[https://core.telegram.org/bots/api#encryptedpassportelement EncryptedPassportElement]]. See the
  * [[https://core.telegram.org/passport#receiving-information Telegram Passport Documentation]] for a complete
  * description of the data decryption and authentication processes.
  */
case class EncryptedCredentials(data: String, hash: String, secret: String)
