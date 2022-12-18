package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import enumeratum.*
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

sealed trait ChatAction extends EnumEntry

object ChatAction extends Enum[ChatAction] {
  case object Typing extends ChatAction
  case object UploadPhoto extends ChatAction
  case object RecordVideo extends ChatAction
  case object UploadVideo extends ChatAction
  case object RecordVoice extends ChatAction
  case object UploadVoice extends ChatAction
  case object UploadDocument extends ChatAction
  case object ChooseSticker extends ChatAction
  case object FindLocation extends ChatAction
  case object RecordVideoNote extends ChatAction
  case object UploadVideoNote extends ChatAction

  override def values: IndexedSeq[ChatAction] = findValues

  implicit lazy val chatActionJsonCodec: JsonValueCodec[ChatAction] = codecs.makeEnumCodec(values)
}
