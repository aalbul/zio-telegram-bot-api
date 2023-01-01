package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.MaskPosition

object AddStickerToSet {

  /** Constructs minimal [[AddStickerToSet]] command
    * @param userId
    *   User identifier of sticker set owner
    * @param name
    *   Sticker set name
    * @param emojis
    *   One or more emoji corresponding to the sticker
    * @return
    *   [[AddStickerToSet]] builder
    */
  def of(userId: Long, name: String, emojis: String): AddStickerToSet = AddStickerToSet(
    userId = userId,
    stickerName = name,
    pngSticker = None,
    tgsSticker = None,
    webmSticker = None,
    emojis = emojis,
    maskPosition = None
  )
}

/** Use this method to add a new sticker to a set created by the bot. You must use exactly one of the fields
  * png_sticker, tgs_sticker, or webm_sticker. Animated stickers can be added to animated sticker sets and only to them.
  * Animated sticker sets can have up to 50 stickers. Static sticker sets can have up to 120 stickers. Returns True on
  * success.
  */
case class AddStickerToSet(
  userId: Long,
  stickerName: String,
  pngSticker: Option[FileDescriptor],
  tgsSticker: Option[FileDescriptor],
  webmSticker: Option[FileDescriptor],
  emojis: String,
  maskPosition: Option[MaskPosition]
) extends Command[Boolean] {
  override val name: String = "addStickerToSet"
  override def parameters: ApiParameters = MultipartBody.ofOpt(
    Some(stringPart("user_id", userId)),
    Some(stringPart("name", stickerName)),
    pngSticker.map(_.asMultipart("png_sticker")),
    tgsSticker.map(_.asMultipart("tgs_sticker")),
    webmSticker.map(_.asMultipart("webm_sticker")),
    Some(stringPart("emojis", emojis)),
    maskPosition.map(stringPart("mask_position", _))
  )

  /** PNG image with the sticker, must be up to 512 kilobytes in size, dimensions must not exceed 512px, and either
    * width or height must be exactly 512px. Pass a file_id as a String to send a file that already exists on the
    * Telegram servers, pass an HTTP URL as a String for Telegram to get a file from the Internet, or upload a new one
    * using multipart/form-data.
    * [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    */
  def withPngSticker(pngSticker: FileDescriptor): AddStickerToSet = copy(pngSticker = Some(pngSticker))

  /** TGS animation with the sticker, uploaded using multipart/form-data. See
    * [[https://core.telegram.org/stickers#animated-sticker-requirements]] for technical requirements
    */
  def withTgsSticker(tgsSticker: FileDescriptor): AddStickerToSet = copy(tgsSticker = Some(tgsSticker))

  /** WEBM video with the sticker, uploaded using multipart/form-data. See
    * [[https://core.telegram.org/stickers#video-sticker-requirements]] for technical requirements
    */
  def withWebmSticker(webmSticker: FileDescriptor): AddStickerToSet = copy(webmSticker = Some(webmSticker))

  /** A JSON-serialized object for position where the mask should be placed on faces
    */
  def withMaskPosition(maskPosition: MaskPosition): AddStickerToSet = copy(maskPosition = Some(maskPosition))
}
