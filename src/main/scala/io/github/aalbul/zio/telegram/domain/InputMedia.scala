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

  /** Constructs minimal [[InputMediaAnimation]]
    * @param media
    *   File to send. Pass a file_id to send a file that exists on the Telegram servers (recommended), pass an HTTP URL
    *   for Telegram to get a file from the Internet, or pass “attach://<file_attach_name>” to upload a new one using
    *   multipart/form-data under <file_attach_name> name.
    *   [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    * @return
    *   [[InputMediaAnimation]] builder
    */
  def of(media: FileDescriptor): InputMediaAnimation = InputMediaAnimation(
    media = media,
    thumb = None,
    caption = None,
    parseMode = None,
    captionEntities = None,
    width = None,
    height = None,
    duration = None,
    hasSpoiler = None
  )
}

/** Represents an animation file (GIF or H.264/MPEG-4 AVC video without sound) to be sent.
  */
@named("animation")
case class InputMediaAnimation(
  media: FileDescriptor,
  thumb: Option[FileDescriptor],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  width: Option[Long],
  height: Option[Long],
  duration: Option[Long],
  hasSpoiler: Option[Boolean]
) extends InputMedia {

  /** Thumbnail of the file sent; can be ignored if thumbnail generation for the file is supported server-side. The
    * thumbnail should be in JPEG format and less than 200 kB in size. A thumbnail's width and height should not exceed
    * 320. Ignored if the file is not uploaded using multipart/form-data. Thumbnails can't be reused and can be only
    * uploaded as a new file, so you can pass “attach://<file_attach_name>” if the thumbnail was uploaded using
    * multipart/form-data under <file_attach_name>.
    * [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    */
  def withThumb(thumb: FileDescriptor): InputMediaAnimation = copy(thumb = Some(thumb))

  /** Caption of the animation to be sent, 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): InputMediaAnimation = copy(caption = Some(caption))

  /** Mode for parsing entities in the animation caption.
    * [[https://core.telegram.org/bots/api#formatting-options See formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): InputMediaAnimation = copy(parseMode = Some(parseMode))

  /** List of special entities that appear in the caption, which can be specified instead of parse_mode
    */
  def withCaptionEntities(entities: Seq[MessageEntity]): InputMediaAnimation = copy(captionEntities = Some(entities))

  /** Animation width
    */
  def withWidth(width: Long): InputMediaAnimation = copy(width = Some(width))

  /** Animation height
    */
  def withHeight(height: Long): InputMediaAnimation = copy(height = Some(height))

  /** Animation duration in seconds
    */
  def withDuration(duration: Long): InputMediaAnimation = copy(duration = Some(duration))

  /** Pass True if the animation needs to be covered with a spoiler animation
    */
  def withHasSpoiler(hasSpoiler: Boolean): InputMediaAnimation = copy(hasSpoiler = Some(hasSpoiler))
}

object InputMediaAudio {

  /** Constructs minimal [[InputMediaAudio]]
    * @param media
    *   File to send. Pass a file_id to send a file that exists on the Telegram servers (recommended), pass an HTTP URL
    *   for Telegram to get a file from the Internet, or pass “attach://<file_attach_name>” to upload a new one using
    *   multipart/form-data under <file_attach_name> name.
    *   [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    * @return
    *   [[InputMediaAudio]] builder
    */
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

/** Represents an audio file to be treated as music to be sent.
  */
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

  /** Thumbnail of the file sent; can be ignored if thumbnail generation for the file is supported server-side. The
    * thumbnail should be in JPEG format and less than 200 kB in size. A thumbnail's width and height should not exceed
    * 320. Ignored if the file is not uploaded using multipart/form-data. Thumbnails can't be reused and can be only
    * uploaded as a new file, so you can pass “attach://<file_attach_name>” if the thumbnail was uploaded using
    * multipart/form-data under <file_attach_name>.
    * [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    */
  def withThumb(thumb: FileDescriptor): InputMediaAudio = copy(thumb = Some(thumb))

  /** Caption of the audio to be sent, 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): InputMediaAudio = copy(caption = Some(caption))

  /** Mode for parsing entities in the audio caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): InputMediaAudio = copy(parseMode = Some(parseMode))

  /** List of special entities that appear in the caption, which can be specified instead of parse_mode
    */
  def withCaptionEntities(entities: Seq[MessageEntity]): InputMediaAudio = copy(captionEntities = Some(entities))

  /** Duration of the audio in seconds
    */
  def withDuration(duration: Long): InputMediaAudio = copy(duration = Some(duration))

  /** Performer of the audio
    */
  def withPerformer(performer: String): InputMediaAudio = copy(performer = Some(performer))

  /** Title of the audio
    */
  def withTitle(title: String): InputMediaAudio = copy(title = Some(title))
}

object InputMediaDocument {

  /** Constructs minimal [[InputMediaDocument]]
    * @param media
    *   File to send. Pass a file_id to send a file that exists on the Telegram servers (recommended), pass an HTTP URL
    *   for Telegram to get a file from the Internet, or pass “attach://<file_attach_name>” to upload a new one using
    *   multipart/form-data under <file_attach_name> name.
    *   [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    * @return
    *   [[InputMediaDocument]] builder
    */
  def of(media: FileDescriptor): InputMediaDocument = InputMediaDocument(
    media = media,
    thumb = None,
    caption = None,
    parseMode = None,
    captionEntities = None,
    disableContentTypeDetection = None
  )
}

/** Represents a general file to be sent.
  */
@named("document")
case class InputMediaDocument(
  media: FileDescriptor,
  thumb: Option[FileDescriptor],
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  disableContentTypeDetection: Option[Boolean]
) extends InputMedia {

  /** Thumbnail of the file sent; can be ignored if thumbnail generation for the file is supported server-side. The
    * thumbnail should be in JPEG format and less than 200 kB in size. A thumbnail's width and height should not exceed
    * 320. Ignored if the file is not uploaded using multipart/form-data. Thumbnails can't be reused and can be only
    * uploaded as a new file, so you can pass “attach://<file_attach_name>” if the thumbnail was uploaded using
    * multipart/form-data under <file_attach_name>.
    * [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    */
  def withThumb(thumb: FileDescriptor): InputMediaDocument = copy(thumb = Some(thumb))

  /** Caption of the document to be sent, 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): InputMediaDocument = copy(caption = Some(caption))

  /** Mode for parsing entities in the document caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details
    */
  def withParseMode(parseMode: ParseMode): InputMediaDocument = copy(parseMode = Some(parseMode))

  /** List of special entities that appear in the caption, which can be specified instead of parse_mode
    */
  def withCaptionEntities(entities: Seq[MessageEntity]): InputMediaDocument = copy(captionEntities = Some(entities))

  /** Disables automatic server-side content type detection for files uploaded using multipart/form-data. Always True,
    * if the document is sent as part of an album.
    */
  def withDisableContentTypeDetection(disable: Boolean): InputMediaDocument =
    copy(disableContentTypeDetection = Some(disable))
}

object InputMediaPhoto {

  /** Constructs minimal [[InputMediaPhoto]]
    * @param media
    *   File to send. Pass a file_id to send a file that exists on the Telegram servers (recommended), pass an HTTP URL
    *   for Telegram to get a file from the Internet, or pass “attach://<file_attach_name>” to upload a new one using
    *   multipart/form-data under <file_attach_name> name.
    *   [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    * @return
    *   [[InputMediaPhoto]] builder
    */
  def of(media: FileDescriptor): InputMediaPhoto = InputMediaPhoto(
    media = media,
    caption = None,
    parseMode = None,
    captionEntities = None,
    hasSpoiler = None
  )
}

/** Represents a photo to be sent.
  */
@named("photo")
case class InputMediaPhoto(
  media: FileDescriptor,
  caption: Option[String],
  parseMode: Option[ParseMode],
  captionEntities: Option[Seq[MessageEntity]],
  hasSpoiler: Option[Boolean]
) extends InputMedia {
  override val thumb: Option[FileDescriptor] = None

  /** Caption of the photo to be sent, 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): InputMediaPhoto = copy(caption = Some(caption))

  /** Mode for parsing entities in the photo caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): InputMediaPhoto = copy(parseMode = Some(parseMode))

  /** List of special entities that appear in the caption, which can be specified instead of parse_mode
    */
  def withCaptionEntities(entities: Seq[MessageEntity]): InputMediaPhoto = copy(captionEntities = Some(entities))

  /** Pass True if the photo needs to be covered with a spoiler animation
    */
  def withHasSpoiler(hasSpoiler: Boolean): InputMediaPhoto = copy(hasSpoiler = Some(hasSpoiler))
}

object InputMediaVideo {

  /** Constructs minimal [[InputMediaVideo]]
    * @param media
    *   File to send. Pass a file_id to send a file that exists on the Telegram servers (recommended), pass an HTTP URL
    *   for Telegram to get a file from the Internet, or pass “attach://<file_attach_name>” to upload a new one using
    *   multipart/form-data under <file_attach_name> name.
    *   [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    * @return
    *   [[InputMediaVideo]] builder
    */
  def of(media: FileDescriptor): InputMediaVideo = InputMediaVideo(
    media = media,
    thumb = None,
    caption = None,
    parseMode = None,
    captionEntities = None,
    width = None,
    height = None,
    duration = None,
    supportsStreaming = None,
    hasSpoiler = None
  )
}

/** Represents a video to be sent.
  */
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
  supportsStreaming: Option[Boolean],
  hasSpoiler: Option[Boolean]
) extends InputMedia {

  /** Thumbnail of the file sent; can be ignored if thumbnail generation for the file is supported server-side. The
    * thumbnail should be in JPEG format and less than 200 kB in size. A thumbnail's width and height should not exceed
    * 320. Ignored if the file is not uploaded using multipart/form-data. Thumbnails can't be reused and can be only
    * uploaded as a new file, so you can pass “attach://<file_attach_name>” if the thumbnail was uploaded using
    * multipart/form-data under <file_attach_name>.
    * [[https://core.telegram.org/bots/api#sending-files More information on Sending Files »]]
    */
  def withThumb(thumb: FileDescriptor): InputMediaVideo = copy(thumb = Some(thumb))

  /** Caption of the video to be sent, 0-1024 characters after entities parsing
    */
  def withCaption(caption: String): InputMediaVideo = copy(caption = Some(caption))

  /** Mode for parsing entities in the video caption. See
    * [[https://core.telegram.org/bots/api#formatting-options formatting options]] for more details.
    */
  def withParseMode(parseMode: ParseMode): InputMediaVideo = copy(parseMode = Some(parseMode))

  /** List of special entities that appear in the caption, which can be specified instead of parse_mode
    */
  def withCaptionEntities(entities: Seq[MessageEntity]): InputMediaVideo = copy(captionEntities = Some(entities))

  /** Video width
    */
  def withWidth(width: Long): InputMediaVideo = copy(width = Some(width))

  /** Video height
    */
  def withHeight(height: Long): InputMediaVideo = copy(height = Some(height))

  /** Video duration in seconds
    */
  def withDuration(duration: Long): InputMediaVideo = copy(duration = Some(duration))

  /** Pass True if the uploaded video is suitable for streaming
    */
  def withSupportsStreaming(supports: Boolean): InputMediaVideo = copy(supportsStreaming = Some(supports))

  /** Pass True if the video needs to be covered with a spoiler animation
    */
  def withHasSpoiler(hasSpoiler: Boolean): InputMediaVideo = copy(hasSpoiler = Some(hasSpoiler))
}
