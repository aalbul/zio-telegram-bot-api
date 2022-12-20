package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object Document {
  implicit val documentJsonCodec: JsonValueCodec[Document] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[Document]] object
    * @param fileId
    *   Identifier for this file, which can be used to download or reuse the file
    * @param fileUniqueId
    *   Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be
    *   used to download or reuse the file.
    * @return
    *   [[Document]] builder
    */
  def of(fileId: String, fileUniqueId: String): Document = Document(
    fileId = fileId,
    fileUniqueId = fileUniqueId,
    thumb = None,
    fileName = None,
    mimeType = None,
    fileSize = None
  )
}

/** This object represents a general file (as opposed to [[https://core.telegram.org/bots/api#photosize photos]],
  * [[https://core.telegram.org/bots/api#voice voice messages]] and
  * [[https://core.telegram.org/bots/api#audio audio files]]).
  */
case class Document(
  fileId: String,
  fileUniqueId: String,
  thumb: Option[PhotoSize],
  fileName: Option[String],
  mimeType: Option[String],
  fileSize: Option[Long]
) {

  /** Document thumbnail as defined by sender
    */
  def withThumb(thumb: PhotoSize): Document = copy(thumb = Some(thumb))

  /** Original filename as defined by sender
    */
  def withFileName(fileName: String): Document = copy(fileName = Some(fileName))

  /** MIME type of the file as defined by sender
    */
  def withMimeType(mimeType: String): Document = copy(mimeType = Some(mimeType))

  /** File size in bytes. It can be bigger than 2^31 and some programming languages may have difficulty/silent defects
    * in interpreting it. But it has at most 52 significant bits, so a signed 64-bit integer or double-precision float
    * type are safe for storing this value.
    */
  def withFileSize(fileSize: Long): Document = copy(fileSize = Some(fileSize))
}
