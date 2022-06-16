package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.command.FileDescriptor
import io.github.aalbul.zio.telegram.domain.ParseModes.ParseMode
import io.circe.generic.extras.ConfiguredJsonCodec
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object InputMediaAnimation {
  def of(media: FileDescriptor): InputMediaAnimation = InputMediaAnimation(
    media = media,
    thumb = None,
    caption = None,
    parseMode = None,
    captionEntities = None,
    width = None,
    height = None,
    duration = None
  )
}

@ConfiguredJsonCodec(encodeOnly = true)
case class InputMediaAnimation(
  media: FileDescriptor,
  thumb: Option[FileDescriptor],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  width: Option[Long],
  height: Option[Long],
  duration: Option[Long],
  override val `type`: String = "animation"
) extends InputMedia {
  def withThumb(thumb: FileDescriptor): InputMediaAnimation = copy(thumb = Some(thumb))
  def withCaption(caption: String): InputMediaAnimation = copy(caption = Some(caption))
  def withParseMode(parseMode: ParseMode): InputMediaAnimation = copy(parseMode = Some(parseMode))
  def withCaptionEntities(entities: Seq[MessageEntity]): InputMediaAnimation = copy(captionEntities = Some(entities))
  def withWidth(width: Long): InputMediaAnimation = copy(width = Some(width))
  def withHeight(height: Long): InputMediaAnimation = copy(height = Some(height))
  def withDuration(duration: Long): InputMediaAnimation = copy(duration = Some(duration))
}
