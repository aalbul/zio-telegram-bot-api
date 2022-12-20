package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object PhotoSize {
  implicit val photoSizeJsonCodec: JsonValueCodec[PhotoSize] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[PhotoSize]]
    * @param fileId
    *   Identifier for this file, which can be used to download or reuse the file
    * @param fileUniqueId
    *   Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be
    *   used to download or reuse the file.
    * @param width
    *   Photo width
    * @param height
    *   Photo height
    * @return
    *   [[PhotoSize]] builder
    */
  def of(fileId: String, fileUniqueId: String, width: Long, height: Long): PhotoSize = PhotoSize(
    fileId = fileId,
    fileUniqueId = fileUniqueId,
    width = width,
    height = height,
    fileSize = None
  )
}

/** This object represents one size of a photo or a [[https://core.telegram.org/bots/api#document file]] /
  * [[https://core.telegram.org/bots/api#sticker sticker]] thumbnail.
  */
case class PhotoSize(fileId: String, fileUniqueId: String, width: Long, height: Long, fileSize: Option[Long]) {

  /** File size in bytes
    */
  def withFileSize(fileSize: Long): PhotoSize = copy(fileSize = Some(fileSize))
}
