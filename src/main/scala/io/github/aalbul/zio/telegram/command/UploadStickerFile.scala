package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.File

object UploadStickerFile {

  /** Constructs minimal [[UploadStickerFile]] command
    * @param userId
    *   User identifier of sticker file owner
    * @param pngSticker
    *   PNG image with the sticker, must be up to 512 kilobytes in size, dimensions must not exceed 512px, and either
    *   width or height must be exactly 512px.
    *   [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    * @return
    *   [[UploadStickerFile]] builder
    */
  def of(userId: Long, pngSticker: FileDescriptor): UploadStickerFile =
    UploadStickerFile(userId = userId, pngSticker = pngSticker)
}

/** Use this method to upload a .PNG file with a sticker for later use in createNewStickerSet and addStickerToSet
  * methods (can be used multiple times). Returns the uploaded [[https://core.telegram.org/bots/api#file File]] on
  * success.
  */
case class UploadStickerFile(userId: Long, pngSticker: FileDescriptor) extends Command[File] {
  override val name: String = "uploadStickerFile"
  override def parameters: ApiParameters = MultipartBody.of(
    stringPart("user_id", userId),
    pngSticker.asMultipart("png_sticker")
  )
}
