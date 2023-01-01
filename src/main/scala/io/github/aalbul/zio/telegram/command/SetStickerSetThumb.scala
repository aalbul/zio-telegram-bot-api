package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object SetStickerSetThumb {

  /** Constructs minimal [[SetStickerSetThumb]] command
    * @param name
    *   Sticker set name
    * @param userId
    *   User identifier of the sticker set owner
    * @return
    *   [[SetStickerSetThumb]] builder
    */
  def of(name: String, userId: Long): SetStickerSetThumb = SetStickerSetThumb(
    stickerName = name,
    userId = userId,
    thumb = None
  )
}

/** Use this method to set the thumbnail of a sticker set. Animated thumbnails can be set for animated sticker sets
  * only. Video thumbnails can be set only for video sticker sets only. Returns True on success.
  */
case class SetStickerSetThumb(stickerName: String, userId: Long, thumb: Option[FileDescriptor])
  extends Command[Boolean] {
  override val name: String = "setStickerSetThumb"
  override def parameters: ApiParameters = MultipartBody.ofOpt(
    Some(stringPart("name", stickerName)),
    Some(stringPart("user_id", userId)),
    thumb.map(_.asMultipart("thumb"))
  )

  /** A PNG image with the thumbnail, must be up to 128 kilobytes in size and have width and height exactly 100px, or a
    * TGS animation with the thumbnail up to 32 kilobytes in size; see
    * [[https://core.telegram.org/stickers#animated-sticker-requirements]] for animated sticker technical requirements,
    * or a WEBM video with the thumbnail up to 32 kilobytes in size; see
    * [[https://core.telegram.org/stickers#video-sticker-requirements]] for video sticker technical requirements. Pass a
    * file_id as a String to send a file that already exists on the Telegram servers, pass an HTTP URL as a String for
    * Telegram to get a file from the Internet, or upload a new one using multipart/form-data.
    * [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]. Animated sticker set
    * thumbnails can't be uploaded via HTTP URL.
    */
  def withThumb(thumb: FileDescriptor): SetStickerSetThumb = copy(thumb = Some(thumb))
}
