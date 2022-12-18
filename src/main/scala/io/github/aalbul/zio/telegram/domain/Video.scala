package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object Video {

  /** Constructs minimal [[Video]]
    * @param fileId
    *   Identifier for this file, which can be used to download or reuse the file
    * @param fileUniqueId
    *   Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be
    *   used to download or reuse the file.
    * @param width
    *   Video width as defined by sender
    * @param height
    *   Video height as defined by sender
    * @param duration
    *   Duration of the video in seconds as defined by sender
    * @return
    *   [[Video]] builder
    */
  def of(fileId: String, fileUniqueId: String, width: Int, height: Int, duration: Int): Video = Video(
    fileId = fileId,
    fileUniqueId = fileUniqueId,
    width = width,
    height = height,
    duration = duration,
    thumb = None,
    fileName = None,
    mimeType = None,
    fileSize = None
  )

  implicit val videoJsonCodec: JsonValueCodec[Video] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

/** This object represents a video file.
  */
case class Video(
  fileId: String,
  fileUniqueId: String,
  width: Int,
  height: Int,
  duration: Int,
  thumb: Option[PhotoSize],
  fileName: Option[String],
  mimeType: Option[String],
  fileSize: Option[Int]
) {

  /** Video thumbnail
    */
  def withThumb(thumb: PhotoSize): Video = copy(thumb = Some(thumb))

  /** Original filename as defined by sender
    */
  def withFileName(fileName: String): Video = copy(fileName = Some(fileName))

  /** MIME type of the file as defined by sender
    */
  def withMimeType(mimeType: String): Video = copy(mimeType = Some(mimeType))

  /** File size in bytes. It can be bigger than 2^31 and some programming languages may have difficulty/silent defects
    * in interpreting it. But it has at most 52 significant bits, so a signed 64-bit integer or double-precision float
    * type are safe for storing this value.
    */
  def withFileSize(fileSize: Int): Video = copy(fileSize = Some(fileSize))
}
