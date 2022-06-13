package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.command.FileDescriptor
import io.circe.syntax.EncoderOps
import io.circe.{Encoder, Json}

object InputMedia {
  implicit val inputMediaEncoder: Encoder[InputMedia] = Encoder.instance {
    case animation: InputMediaAnimation => animation.asJson
    case audio: InputMediaAudio         => audio.asJson
    case document: InputMediaDocument   => document.asJson
    case photo: InputMediaPhoto         => photo.asJson
    case video: InputMediaVideo         => video.asJson
    case _                              => Json.Null
  }
}

trait InputMedia {
  val `type`: String
  val media: FileDescriptor
  val thumb: Option[FileDescriptor]
}
