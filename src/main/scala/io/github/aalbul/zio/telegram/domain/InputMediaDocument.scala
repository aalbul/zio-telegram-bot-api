package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.command.FileDescriptor
import io.github.aalbul.zio.telegram.domain.ParseModes.ParseMode
import io.circe.generic.extras.ConfiguredJsonCodec

object InputMediaDocument {
  def of(media: FileDescriptor): InputMediaDocument = InputMediaDocument(
    media = media,
    thumb = None,
    caption = None,
    parseMode = None,
    captionEntities = None,
    disableContentTypeDetection = None
  )
}

@ConfiguredJsonCodec(encodeOnly = true)
case class InputMediaDocument(
  media: FileDescriptor,
  thumb: Option[FileDescriptor],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  disableContentTypeDetection: Option[Boolean],
  override val `type`: String = "document"
) extends InputMedia {
  def withThumb(thumb: FileDescriptor): InputMediaDocument = copy(thumb = Some(thumb))
  def withCaption(caption: String): InputMediaDocument = copy(caption = Some(caption))
  def withParseMode(parseMode: ParseMode): InputMediaDocument = copy(parseMode = Some(parseMode))
  def withCaptionEntities(entities: Seq[MessageEntity]): InputMediaDocument = copy(captionEntities = Some(entities))
  def withDisableContentTypeDetection(disable: Boolean): InputMediaDocument =
    copy(disableContentTypeDetection = Some(disable))
}
