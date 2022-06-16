package io.github.aalbul.zio.telegram.domain

import io.circe.Encoder

object ChatActions extends Enumeration {
  implicit val chatActionEncoder: Encoder[ChatAction] = Encoder.encodeString.contramap(_.toString.camelToSnakeCase)

  type ChatAction = Value

  val Typing, UploadPhoto, RecordVideo, UploadVideo, RecordVoice, UploadVoice, UploadDocument, ChooseSticker,
    FindLocation, RecordVideoNote, UploadVideoNote = Value
}
