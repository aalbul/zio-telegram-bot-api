package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import scala.concurrent.duration.Duration

object Animation {
  implicit val animationJsonCodec: JsonValueCodec[Animation] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[Animation]] object
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
    *   [[Animation]] builder
    */
  def of(fileId: String, fileUniqueId: String, width: Long, height: Long, duration: Duration): Animation = Animation(
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
}

/** This object represents an animation file (GIF or H.264/MPEG-4 AVC video without sound).
  */
case class Animation(
  fileId: String,
  fileUniqueId: String,
  width: Long,
  height: Long,
  duration: Duration,
  thumb: Option[PhotoSize],
  fileName: Option[String],
  mimeType: Option[String],
  fileSize: Option[Long]
) {

  /** Animation thumbnail as defined by sender
    */
  def withThumb(thumb: PhotoSize): Animation = copy(thumb = Some(thumb))

  /** Original animation filename as defined by sender
    */
  def withFileName(fileName: String): Animation = copy(fileName = Some(fileName))

  /** MIME type of the file as defined by sender
    */
  def withMimeType(mimeType: String): Animation = copy(mimeType = Some(mimeType))

  /** File size in bytes. It can be bigger than 2^31 and some programming languages may have difficulty/silent defects
    * in Longerpreting it. But it has at most 52 significant bits, so a signed 64-bit Longeger or double-precision float
    * type are safe for storing this value.
    */
  def withFileSize(fileSize: Long): Animation = copy(fileSize = Some(fileSize))
}
