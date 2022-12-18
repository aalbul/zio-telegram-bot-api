package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{named, CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.command.FileDescriptor
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.*

object InputMedia {
  implicit val inputMediaJsonCodec: JsonValueCodec[InputMedia] = JsonCodecMaker.make(
    CodecMakerConfig
      .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
      .withTransientDefault(false)
      .withTransientNone(false)
      .withTransientEmpty(false)
      .withDiscriminatorFieldName(Some("type"))
  )

  implicit val seqInputMediaJsonCodec: JsonValueCodec[Seq[InputMedia]] = JsonCodecMaker.make
}

sealed trait InputMedia {
  val media: FileDescriptor
  val thumb: Option[FileDescriptor]
}

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

@named("animation")
case class InputMediaAnimation(
  media: FileDescriptor,
  thumb: Option[FileDescriptor],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  width: Option[Long],
  height: Option[Long],
  duration: Option[Long]
) extends InputMedia {
  def withThumb(thumb: FileDescriptor): InputMediaAnimation = copy(thumb = Some(thumb))
  def withCaption(caption: String): InputMediaAnimation = copy(caption = Some(caption))
  def withParseMode(parseMode: ParseMode): InputMediaAnimation = copy(parseMode = Some(parseMode))
  def withCaptionEntities(entities: Seq[MessageEntity]): InputMediaAnimation = copy(captionEntities = Some(entities))
  def withWidth(width: Long): InputMediaAnimation = copy(width = Some(width))
  def withHeight(height: Long): InputMediaAnimation = copy(height = Some(height))
  def withDuration(duration: Long): InputMediaAnimation = copy(duration = Some(duration))
}

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

@named("audio")
case class InputMediaAudio(
  media: FileDescriptor,
  thumb: Option[FileDescriptor],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  duration: Option[Long],
  performer: Option[String],
  title: Option[String]
) extends InputMedia {
  def withThumb(thumb: FileDescriptor): InputMediaAudio = copy(thumb = Some(thumb))
  def withCaption(caption: String): InputMediaAudio = copy(caption = Some(caption))
  def withParseMode(parseMode: ParseMode): InputMediaAudio = copy(parseMode = Some(parseMode))
  def withCaptionEntities(entities: Seq[MessageEntity]): InputMediaAudio = copy(captionEntities = Some(entities))
  def withDuration(duration: Long): InputMediaAudio = copy(duration = Some(duration))
  def withPerformer(performer: String): InputMediaAudio = copy(performer = Some(performer))
  def withTitle(title: String): InputMediaAudio = copy(title = Some(title))
}

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

@named("document")
case class InputMediaDocument(
  media: FileDescriptor,
  thumb: Option[FileDescriptor],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  disableContentTypeDetection: Option[Boolean]
) extends InputMedia {
  def withThumb(thumb: FileDescriptor): InputMediaDocument = copy(thumb = Some(thumb))
  def withCaption(caption: String): InputMediaDocument = copy(caption = Some(caption))
  def withParseMode(parseMode: ParseMode): InputMediaDocument = copy(parseMode = Some(parseMode))
  def withCaptionEntities(entities: Seq[MessageEntity]): InputMediaDocument = copy(captionEntities = Some(entities))
  def withDisableContentTypeDetection(disable: Boolean): InputMediaDocument =
    copy(disableContentTypeDetection = Some(disable))
}

object InputMediaPhoto {
  def of(media: FileDescriptor): InputMediaPhoto = InputMediaPhoto(
    media = media,
    thumb = None,
    caption = None,
    parseMode = None,
    captionEntities = None
  )
}

@named("photo")
case class InputMediaPhoto(
  media: FileDescriptor,
  thumb: Option[FileDescriptor],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]]
) extends InputMedia {
  def withThumb(thumb: FileDescriptor): InputMediaPhoto = copy(thumb = Some(thumb))
  def withCaption(caption: String): InputMediaPhoto = copy(caption = Some(caption))
  def withParseMode(parseMode: ParseMode): InputMediaPhoto = copy(parseMode = Some(parseMode))
  def withCaptionEntities(entities: Seq[MessageEntity]): InputMediaPhoto = copy(captionEntities = Some(entities))
}

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

@named("video")
case class InputMediaVideo(
  media: FileDescriptor,
  thumb: Option[FileDescriptor],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  width: Option[Long],
  height: Option[Long],
  duration: Option[Long],
  supportsStreaming: Option[Boolean]
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
