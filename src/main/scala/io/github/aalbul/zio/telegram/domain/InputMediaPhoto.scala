package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.command.FileDescriptor
import io.github.aalbul.zio.telegram.domain.ParseModes.ParseMode
import io.circe.generic.extras.ConfiguredJsonCodec

object InputMediaPhoto {
  def of(media: FileDescriptor): InputMediaPhoto = InputMediaPhoto(
    media = media,
    thumb = None,
    caption = None,
    parseMode = None,
    captionEntities = None
  )
}

@ConfiguredJsonCodec(encodeOnly = true)
case class InputMediaPhoto(
  media: FileDescriptor,
  thumb: Option[FileDescriptor],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  override val `type`: String = "photo"
) extends InputMedia {
  def withThumb(thumb: FileDescriptor): InputMediaPhoto = copy(thumb = Some(thumb))
  def withCaption(caption: String): InputMediaPhoto = copy(caption = Some(caption))
  def withParseMode(parseMode: ParseMode): InputMediaPhoto = copy(parseMode = Some(parseMode))
  def withCaptionEntities(entities: Seq[MessageEntity]): InputMediaPhoto = copy(captionEntities = Some(entities))
}
