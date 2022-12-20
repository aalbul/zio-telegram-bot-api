package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

import scala.concurrent.duration.Duration

object Audio {
  implicit val audioJsonCodec: JsonValueCodec[Audio] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[Audio]] object
    * @param fileId
    *   Identifier for this file, which can be used to download or reuse the file
    * @param fileUniqueId
    *   Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be
    *   used to download or reuse the file.
    * @param duration
    *   Duration of the audio in seconds as defined by sender
    * @return
    *   [[Audio]] builder
    */
  def of(fileId: String, fileUniqueId: String, duration: Duration): Audio = Audio(
    fileId = fileId,
    fileUniqueId = fileUniqueId,
    duration = duration,
    performer = None,
    title = None,
    fileName = None,
    mimeType = None,
    fileSize = None,
    thumb = None
  )
}

/** This object represents an audio file to be treated as music by the Telegram clients.
  */
case class Audio(
  fileId: String,
  fileUniqueId: String,
  duration: Duration,
  performer: Option[String],
  title: Option[String],
  fileName: Option[String],
  mimeType: Option[String],
  fileSize: Option[Long],
  thumb: Option[PhotoSize]
) {

  /** Performer of the audio as defined by sender or by audio tags
    */
  def withPerformer(performer: String): Audio = copy(performer = Some(performer))

  /** Title of the audio as defined by sender or by audio tags
    */
  def withTitle(title: String): Audio = copy(title = Some(title))

  /** Original filename as defined by sender
    */
  def withFileName(fileName: String): Audio = copy(fileName = Some(fileName))

  /** MIME type of the file as defined by sender
    */
  def withMimeType(mimeType: String): Audio = copy(mimeType = Some(mimeType))

  /** File size in bytes. It can be bigger than 2^31 and some programming languages may have difficulty/silent defects
    * in Longerpreting it. But it has at most 52 significant bits, so a signed 64-bit Longeger or double-precision float
    * type are safe for storing this value.
    */
  def withFileSize(fileSize: Long): Audio = copy(fileSize = Some(fileSize))

  /** Thumbnail of the album cover to which the music file belongs
    */
  def withThumb(thumb: PhotoSize): Audio = copy(thumb = Some(thumb))
}
