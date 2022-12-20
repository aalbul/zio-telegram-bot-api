package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import java.time.Instant

object PassportFile {
  implicit val passportFileJsonCodec: JsonValueCodec[PassportFile] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[PassportFile]]
    * @param fileId
    *   Identifier for this file, which can be used to download or reuse the file
    * @param fileUniqueId
    *   Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be
    *   used to download or reuse the file.
    * @param fileSize
    *   File size in bytes
    * @param fileDate
    *   Unix time when the file was uploaded
    * @return
    *   [[PassportFile]] builder
    */
  def of(fileId: String, fileUniqueId: String, fileSize: Long, fileDate: Instant): PassportFile =
    PassportFile(fileId = fileId, fileUniqueId = fileUniqueId, fileSize = fileSize, fileDate = fileDate)
}

/** This object represents a file uploaded to Telegram Passport. Currently all Telegram Passport files are in JPEG
  * format when decrypted and don't exceed 10MB.
  */
case class PassportFile(fileId: String, fileUniqueId: String, fileSize: Long, fileDate: Instant)
