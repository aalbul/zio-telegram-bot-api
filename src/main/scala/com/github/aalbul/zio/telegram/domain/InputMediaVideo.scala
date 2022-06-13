package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.command.FileDescriptor
import com.github.aalbul.zio.telegram.domain.ParseModes.ParseMode
import io.circe.generic.extras.ConfiguredJsonCodec

object InputMediaVideo {
  def of(media: FileDescriptor): InputMediaVideo = InputMediaVideo(
    media = media,
    thumb = None,
    caption = None,
    parseMode = None,
    captionEntities = None,
    width = None,
    height = None,
    duration = None,
    supportsStreaming = None
  )
}

@ConfiguredJsonCodec(encodeOnly = true)
case class InputMediaVideo(
  media: FileDescriptor,
  thumb: Option[FileDescriptor],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  width: Option[Long],
  height: Option[Long],
  duration: Option[Long],
  supportsStreaming: Option[Boolean],
  override val `type`: String = "video"
) extends InputMedia {
  def withThumb(thumb: FileDescriptor): InputMediaVideo = copy(thumb = Some(thumb))
  def withCaption(caption: String): InputMediaVideo = copy(caption = Some(caption))
  def withParseMode(parseMode: ParseMode): InputMediaVideo = copy(parseMode = Some(parseMode))
  def withCaptionEntities(entities: Seq[MessageEntity]): InputMediaVideo = copy(captionEntities = Some(entities))
  def withWidth(width: Long): InputMediaVideo = copy(width = Some(width))
  def withHeight(height: Long): InputMediaVideo = copy(height = Some(height))
  def withDuration(duration: Long): InputMediaVideo = copy(duration = Some(duration))
  def withSupportsStreaming(supports: Boolean): InputMediaVideo = copy(supportsStreaming = Some(supports))
}
