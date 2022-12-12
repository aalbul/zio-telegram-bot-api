package io.github.aalbul.zio.telegram.domain

import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object Voice {

  /** Constructs minimal [[Voice]]
    * @param fileId
    *   Identifier for this file, which can be used to download or reuse the file
    * @param fileUniqueId
    *   Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be
    *   used to download or reuse the file.
    * @param duration
    *   Duration of the audio in seconds as defined by sender
    * @return
    *   [[Voice]] builder
    */
  def of(fileId: String, fileUniqueId: String, duration: Int): Voice = Voice(
    fileId = fileId,
    fileUniqueId = fileUniqueId,
    duration = duration,
    mimeType = None,
    fileSize = None
  )
}

/** This object represents a voice note.
  */
@ConfiguredJsonCodec(decodeOnly = true)
case class Voice(
  fileId: String,
  fileUniqueId: String,
  duration: Int,
  mimeType: Option[String],
  fileSize: Option[Int]
) {

  /** MIME type of the file as defined by sender
    */
  def withMimeType(mimeType: String): Voice = copy(mimeType = Some(mimeType))

  /** File size in bytes. It can be bigger than 2^31 and some programming languages may have difficulty/silent defects
    * in interpreting it. But it has at most 52 significant bits, so a signed 64-bit integer or double-precision float
    * type are safe for storing this value.
    */
  def withFileSize(fileSize: Int): Voice = copy(fileSize = Some(fileSize))
}
