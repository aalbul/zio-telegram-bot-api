package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object ChatPhoto {
  implicit val chatPhotoJsonCodec: JsonValueCodec[ChatPhoto] = JsonCodecMaker.make(
    CodecMakerConfig.withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
  )

  /** Constructs minimal [[ChatPhoto]] object
    * @param smallFileId
    *   File identifier of small (160x160) chat photo. This file_id can be used only for photo download and only for as
    *   long as the photo is not changed.
    * @param smallFileUniqueId
    *   Unique file identifier of small (160x160) chat photo, which is supposed to be the same over time and for
    *   different bots. Can't be used to download or reuse the file.
    * @param bigFileId
    *   File identifier of big (640x640) chat photo. This file_id can be used only for photo download and only for as
    *   long as the photo is not changed.
    * @param bigFileUniqueId
    *   Unique file identifier of big (640x640) chat photo, which is supposed to be the same over time and for different
    *   bots. Can't be used to download or reuse the file.
    * @return
    *   [[ChatPhoto]] builder
    */
  def of(smallFileId: String, smallFileUniqueId: String, bigFileId: String, bigFileUniqueId: String): ChatPhoto =
    ChatPhoto(smallFileId, smallFileUniqueId, bigFileId, bigFileUniqueId)
}

/** This object represents a chat photo.
  */
case class ChatPhoto(smallFileId: String, smallFileUniqueId: String, bigFileId: String, bigFileUniqueId: String)
