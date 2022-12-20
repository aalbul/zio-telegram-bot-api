package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object File {
  implicit val fileJsonCodec: JsonValueCodec[File] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[File]] object
    * @param fileId
    *   Identifier for this file, which can be used to download or reuse the file
    * @param fileUniqueId
    *   Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be
    *   used to download or reuse the file.
    * @return
    *   [[File]] builder
    */
  def of(fileId: String, fileUniqueId: String): File = File(
    fileId = fileId,
    fileUniqueId = fileUniqueId,
    fileSize = None,
    filePath = None
  )
}

/** This object represents a file ready to be downloaded. The file can be downloaded via the link
  * `https://api.telegram.org/file/bot<token>/<file_path>`. It is guaranteed that the link will be valid for at least 1
  * hour. When the link expires, a new one can be requested by calling
  * [[https://core.telegram.org/bots/api#getfile getFile]].
  *
  * The maximum file size to download is 20 MB
  */
case class File(fileId: String, fileUniqueId: String, fileSize: Option[Long], filePath: Option[String]) {

  /** File size in bytes. It can be bigger than 2^31 and some programming languages may have difficulty/silent defects
    * in interpreting it. But it has at most 52 significant bits, so a signed 64-bit integer or double-precision float
    * type are safe for storing this value.
    */
  def withFileSize(fileSize: Long): File = copy(fileSize = Some(fileSize))

  /** File path. Use `https://api.telegram.org/file/bot<token>/<file_path>` to get the file.
    */
  def withFilePath(filePath: String): File = copy(filePath = Some(filePath))
}
