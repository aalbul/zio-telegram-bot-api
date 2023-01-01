package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.MultipartBody.stringPart
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*
import io.github.aalbul.zio.telegram.domain.{MaskPosition, StickerType}

object CreateNewStickerSet {

  /** Constructs minimal [[CreateNewStickerSet]] command
    * @param userId
    *   User identifier of created sticker set owner
    * @param name
    *   Short name of sticker set, to be used in t.me/addstickers/ URLs (e.g., animals). Can contain only English
    *   letters, digits and underscores. Must begin with a letter, can't contain consecutive underscores and must end in
    *   "_by_<bot_username>". <bot_username> is case insensitive. 1-64 characters.
    * @param title
    *   Sticker set title, 1-64 characters
    * @param emojis
    *   One or more emoji corresponding to the sticker
    * @return
    *   [[CreateNewStickerSet]] builder
    */
  def of(userId: Long, name: String, title: String, emojis: String): CreateNewStickerSet = CreateNewStickerSet(
    userId = userId,
    stickerName = name,
    title = title,
    pngSticker = None,
    tgsSticker = None,
    webmSticker = None,
    stickerType = None,
    emojis = emojis,
    maskPosition = None
  )
}

/** Use this method to create a new sticker set owned by a user. The bot will be able to edit the sticker set thus
  * created. You must use exactly one of the fields png_sticker, tgs_sticker, or webm_sticker. Returns True on success.
  */
case class CreateNewStickerSet(
  userId: Long,
  stickerName: String,
  title: String,
  pngSticker: Option[FileDescriptor],
  tgsSticker: Option[FileDescriptor],
  webmSticker: Option[FileDescriptor],
  stickerType: Option[StickerType],
  emojis: String,
  maskPosition: Option[MaskPosition]
) extends Command[Boolean] {
  override val name: String = "createNewStickerSet"
  override def parameters: ApiParameters = MultipartBody.ofOpt(
    Some(stringPart("user_id", userId)),
    Some(stringPart("name", stickerName)),
    Some(stringPart("title", title)),
    pngSticker.map(_.asMultipart("png_sticker")),
    tgsSticker.map(_.asMultipart("tgs_sticker")),
    webmSticker.map(_.asMultipart("webm_sticker")),
    stickerType.map(`type` => stringPart("sticker_type", `type`.toString.camelToSnakeCase)),
    Some(stringPart("emojis", emojis)),
    maskPosition.map(stringPart("mask_position", _))
  )

  /** PNG image with the sticker, must be up to 512 kilobytes in size, dimensions must not exceed 512px, and either
    * width or height must be exactly 512px. Pass a file_id as a String to send a file that already exists on the
    * Telegram servers, pass an HTTP URL as a String for Telegram to get a file from the Internet, or upload a new one
    * using multipart/form-data.
    * [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    */
  def withPngSticker(pngSticker: FileDescriptor): CreateNewStickerSet = copy(pngSticker = Some(pngSticker))

  /** TGS animation with the sticker, uploaded using multipart/form-data. See
    * [[https://core.telegram.org/stickers#animated-sticker-requirements]] for technical requirements
    */
  def withTgsSticker(tgsSticker: FileDescriptor): CreateNewStickerSet = copy(tgsSticker = Some(tgsSticker))

  /** WEBM video with the sticker, uploaded using multipart/form-data. See
    * [[https://core.telegram.org/stickers#video-sticker-requirements]] for technical requirements
    */
  def withWebmSticker(webmSticker: FileDescriptor): CreateNewStickerSet = copy(webmSticker = Some(webmSticker))

  /** Type of stickers in the set, pass “regular” or “mask”. Custom emoji sticker sets can't be created via the Bot API
    * at the moment. By default, a regular sticker set is created.
    */
  def withStickerType(stickerType: StickerType): CreateNewStickerSet = copy(stickerType = Some(stickerType))

  /** A JSON-serialized object for position where the mask should be placed on faces
    */
  def withMaskPosition(maskPosition: MaskPosition): CreateNewStickerSet = copy(maskPosition = Some(maskPosition))
}
