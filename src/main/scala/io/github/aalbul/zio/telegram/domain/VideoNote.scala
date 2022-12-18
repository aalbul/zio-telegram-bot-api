package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object VideoNote {

  /** Constructs minimal [[VideoNote]]
    * @param fileId
    *   Identifier for this file, which can be used to download or reuse the file
    * @param fileUniqueId
    *   Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be
    *   used to download or reuse the file.
    * @param length
    *   Video width and height (diameter of the video message) as defined by sender
    * @param duration
    *   Duration of the video in seconds as defined by sender
    * @return
    *   [[VideoNote]] builder
    */
  def of(fileId: String, fileUniqueId: String, length: Int, duration: Int): VideoNote = VideoNote(
    fileId = fileId,
    fileUniqueId = fileUniqueId,
    length = length,
    duration = duration,
    thumb = None,
    fileSize = None
  )

  implicit val videoNoteJsonCodec: JsonValueCodec[VideoNote] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )
}

/** This object represents a [[https://telegram.org/blog/video-messages-and-telescope video message]] (available in
  * Telegram apps as of [[https://telegram.org/blog/video-messages-and-telescope v.4.0]]).
  */
case class VideoNote(
  fileId: String,
  fileUniqueId: String,
  length: Int,
  duration: Int,
  thumb: Option[PhotoSize],
  fileSize: Option[Int]
) {

  /** Video thumbnail
    */
  def withThumb(thumb: PhotoSize): VideoNote = copy(thumb = Some(thumb))

  /** File size in bytes
    */
  def withFileSize(fileSize: Int): VideoNote = copy(fileSize = Some(fileSize))
}
