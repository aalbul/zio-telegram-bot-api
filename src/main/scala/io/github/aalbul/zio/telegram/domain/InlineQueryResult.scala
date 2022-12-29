package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}
import io.github.aalbul.zio.telegram.domain.JsonSerializationSupport.makeEncoderOnly
import io.github.aalbul.zio.telegram.serialization.DefaultJsonCodecs

object InlineQueryResult extends DefaultJsonCodecs {
  private val inlineQueryResultCachedAudioJsonCodec: JsonValueCodec[InlineQueryResultCachedAudio] =
    JsonCodecMaker.make(
      CodecMakerConfig
        .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
        .withTransientDefault(false)
    )

  private val inlineQueryResultCachedDocumentJsonCodec: JsonValueCodec[InlineQueryResultCachedDocument] =
    JsonCodecMaker.make(
      CodecMakerConfig
        .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
        .withTransientDefault(false)
    )

  private val inlineQueryResultCachedGifJsonCodec: JsonValueCodec[InlineQueryResultCachedGif] =
    JsonCodecMaker.make(
      CodecMakerConfig
        .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
        .withTransientDefault(false)
    )

  private val InlineQueryResultCachedMpeg4GifJsonCodec: JsonValueCodec[InlineQueryResultCachedMpeg4Gif] =
    JsonCodecMaker.make(
      CodecMakerConfig
        .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
        .withTransientDefault(false)
    )

  private val inlineQueryResultCachedPhotoJsonCodec: JsonValueCodec[InlineQueryResultCachedPhoto] =
    JsonCodecMaker.make(
      CodecMakerConfig
        .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
        .withTransientDefault(false)
    )

  private val inlineQueryResultCachedStickerJsonCodec: JsonValueCodec[InlineQueryResultCachedSticker] =
    JsonCodecMaker.make(
      CodecMakerConfig
        .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
        .withTransientDefault(false)
    )

  private val inlineQueryResultCachedVideoJsonCodec: JsonValueCodec[InlineQueryResultCachedVideo] =
    JsonCodecMaker.make(
      CodecMakerConfig
        .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
        .withTransientDefault(false)
    )

  private val inlineQueryResultCachedVoiceJsonCodec: JsonValueCodec[InlineQueryResultCachedVoice] =
    JsonCodecMaker.make(
      CodecMakerConfig
        .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
        .withTransientDefault(false)
    )

  private val inlineQueryResultArticleJsonCodec: JsonValueCodec[InlineQueryResultArticle] = JsonCodecMaker.make(
    CodecMakerConfig
      .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
      .withTransientDefault(false)
  )

  private val inlineQueryResultAudioJsonCodec: JsonValueCodec[InlineQueryResultAudio] = JsonCodecMaker.make(
    CodecMakerConfig
      .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
      .withTransientDefault(false)
  )

  private val inlineQueryResultContactJsonCodec: JsonValueCodec[InlineQueryResultContact] = JsonCodecMaker.make(
    CodecMakerConfig
      .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
      .withTransientDefault(false)
  )

  private val inlineQueryResultGameJsonCodec: JsonValueCodec[InlineQueryResultGame] = JsonCodecMaker.make(
    CodecMakerConfig
      .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
      .withTransientDefault(false)
  )

  private val inlineQueryResultDocumentJsonCodec: JsonValueCodec[InlineQueryResultDocument] = JsonCodecMaker.make(
    CodecMakerConfig
      .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
      .withTransientDefault(false)
  )

  private val inlineQueryResultGifJsonCodec: JsonValueCodec[InlineQueryResultGif] = JsonCodecMaker.make(
    CodecMakerConfig
      .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
      .withTransientDefault(false)
  )

  private val inlineQueryResultLocationJsonCodec: JsonValueCodec[InlineQueryResultLocation] = JsonCodecMaker.make(
    CodecMakerConfig
      .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
      .withTransientDefault(false)
  )

  private val inlineQueryResultMpeg4GifJsonCodec: JsonValueCodec[InlineQueryResultMpeg4Gif] = JsonCodecMaker.make(
    CodecMakerConfig
      .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
      .withTransientDefault(false)
  )

  private val inlineQueryResultPhotoJsonCodec: JsonValueCodec[InlineQueryResultPhoto] = JsonCodecMaker.make(
    CodecMakerConfig
      .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
      .withTransientDefault(false)
  )

  private val inlineQueryResultVenueJsonCodec: JsonValueCodec[InlineQueryResultVenue] = JsonCodecMaker.make(
    CodecMakerConfig
      .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
      .withTransientDefault(false)
  )

  private val inlineQueryResultVideoJsonCodec: JsonValueCodec[InlineQueryResultVideo] = JsonCodecMaker.make(
    CodecMakerConfig
      .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
      .withTransientDefault(false)
  )

  private val inlineQueryResultVoiceJsonCodec: JsonValueCodec[InlineQueryResultVoice] = JsonCodecMaker.make(
    CodecMakerConfig
      .withFieldNameMapper(JsonCodecMaker.enforce_snake_case2)
      .withTransientDefault(false)
  )

  implicit val inlineQueryResultJsonCodec: JsonValueCodec[InlineQueryResult] = makeEncoderOnly {
    case (audio: InlineQueryResultCachedAudio, writer) =>
      inlineQueryResultCachedAudioJsonCodec.encodeValue(audio, writer)
    case (document: InlineQueryResultCachedDocument, writer) =>
      inlineQueryResultCachedDocumentJsonCodec.encodeValue(document, writer)
    case (gif: InlineQueryResultCachedGif, writer) => inlineQueryResultCachedGifJsonCodec.encodeValue(gif, writer)
    case (mpeg4gif: InlineQueryResultCachedMpeg4Gif, writer) =>
      InlineQueryResultCachedMpeg4GifJsonCodec.encodeValue(mpeg4gif, writer)
    case (photo: InlineQueryResultCachedPhoto, writer) =>
      inlineQueryResultCachedPhotoJsonCodec.encodeValue(photo, writer)
    case (sticker: InlineQueryResultCachedSticker, writer) =>
      inlineQueryResultCachedStickerJsonCodec.encodeValue(sticker, writer)
    case (video: InlineQueryResultCachedVideo, writer) =>
      inlineQueryResultCachedVideoJsonCodec.encodeValue(video, writer)
    case (voice: InlineQueryResultCachedVoice, writer) =>
      inlineQueryResultCachedVoiceJsonCodec.encodeValue(voice, writer)
    case (article: InlineQueryResultArticle, writer) => inlineQueryResultArticleJsonCodec.encodeValue(article, writer)
    case (audio: InlineQueryResultAudio, writer)     => inlineQueryResultAudioJsonCodec.encodeValue(audio, writer)
    case (contact: InlineQueryResultContact, writer) => inlineQueryResultContactJsonCodec.encodeValue(contact, writer)
    case (game: InlineQueryResultGame, writer)       => inlineQueryResultGameJsonCodec.encodeValue(game, writer)
    case (document: InlineQueryResultDocument, writer) =>
      inlineQueryResultDocumentJsonCodec.encodeValue(document, writer)
    case (gif: InlineQueryResultGif, writer) => inlineQueryResultGifJsonCodec.encodeValue(gif, writer)
    case (location: InlineQueryResultLocation, writer) =>
      inlineQueryResultLocationJsonCodec.encodeValue(location, writer)
    case (gif: InlineQueryResultMpeg4Gif, writer) => inlineQueryResultMpeg4GifJsonCodec.encodeValue(gif, writer)
    case (photo: InlineQueryResultPhoto, writer)  => inlineQueryResultPhotoJsonCodec.encodeValue(photo, writer)
    case (venue: InlineQueryResultVenue, writer)  => inlineQueryResultVenueJsonCodec.encodeValue(venue, writer)
    case (video: InlineQueryResultVideo, writer)  => inlineQueryResultVideoJsonCodec.encodeValue(video, writer)
    case (voice: InlineQueryResultVoice, writer)  => inlineQueryResultVoiceJsonCodec.encodeValue(voice, writer)
  }
}

trait InlineQueryResult {
  val `type`: String
}
