package com.github.aalbul.zio.telegram.domain

import com.github.aalbul.zio.telegram.command.FileDescriptor
import com.github.aalbul.zio.telegram.domain.ParseModes.ParseMode
import io.circe.generic.extras.ConfiguredJsonCodec

object InputMediaAudio {
  def of(media: FileDescriptor): InputMediaAudio = InputMediaAudio(
    media = media,
    thumb = None,
    caption = None,
    parseMode = None,
    captionEntities = None,
    duration = None,
    performer = None,
    title = None
  )
}

@ConfiguredJsonCodec(encodeOnly = true)
case class InputMediaAudio(
  media: FileDescriptor,
  thumb: Option[FileDescriptor],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  duration: Option[Long],
  performer: Option[String],
  title: Option[String],
  override val `type`: String = "audio"
) extends InputMedia {
  def withThumb(thumb: FileDescriptor): InputMediaAudio = copy(thumb = Some(thumb))
  def withCaption(caption: String): InputMediaAudio = copy(caption = Some(caption))
  def withParseMode(parseMode: ParseMode): InputMediaAudio = copy(parseMode = Some(parseMode))
  def withCaptionEntities(entities: Seq[MessageEntity]): InputMediaAudio = copy(captionEntities = Some(entities))
  def withDuration(duration: Long): InputMediaAudio = copy(duration = Some(duration))
  def withPerformer(performer: String): InputMediaAudio = copy(performer = Some(performer))
  def withTitle(title: String): InputMediaAudio = copy(title = Some(title))
}
