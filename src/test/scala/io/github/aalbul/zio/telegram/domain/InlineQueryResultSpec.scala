package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class InlineQueryResultSpec extends BaseSpec {
  "InlineQueryResult" when {
    "encoder" should {
      "encode InlineQueryResultCachedAudio to json" in {
        writeToString(inlineQueryResult1) should matchJsonResource("json/model/inline-query-result-cached-audio.json")
      }

      "encode InlineQueryResultCachedDocument to json" in {
        writeToString(inlineQueryResult2) should matchJsonResource(
          "json/model/inline-query-result-cached-document.json"
        )
      }

      "encode InlineQueryResultCachedGif to json" in {
        writeToString(inlineQueryResult3) should matchJsonResource("json/model/inline-query-result-cached-gif.json")
      }

      "encode InlineQueryResultCachedMpeg4Gif to json" in {
        writeToString(inlineQueryResult4) should matchJsonResource(
          "json/model/inline-query-result-cached-mpeg-4-gif.json"
        )
      }

      "encode InlineQueryResultCachedPhoto to json" in {
        writeToString(inlineQueryResult5) should matchJsonResource("json/model/inline-query-result-cached-photo.json")
      }

      "encode InlineQueryResultCachedSticker to json" in {
        writeToString(inlineQueryResult6) should matchJsonResource("json/model/inline-query-result-cached-sticker.json")
      }

      "encode InlineQueryResultCachedVideo to json" in {
        writeToString(inlineQueryResult7) should matchJsonResource("json/model/inline-query-result-cached-video.json")
      }

      "encode InlineQueryResultCachedVoice to json" in {
        writeToString(inlineQueryResult8) should matchJsonResource("json/model/inline-query-result-cached-voice.json")
      }

      "encode InlineQueryResultArticle to json" in {
        writeToString(inlineQueryResult9) should matchJsonResource("json/model/inline-query-result-article.json")
      }

      "encode InlineQueryResultAudio to json" in {
        writeToString(inlineQueryResult10) should matchJsonResource("json/model/inline-query-result-audio.json")
      }

      "encode InlineQueryResultContact to json" in {
        writeToString(inlineQueryResult11) should matchJsonResource("json/model/inline-query-result-contact.json")
      }

      "encode InlineQueryResultGame to json" in {
        writeToString(inlineQueryResult12) should matchJsonResource("json/model/inline-query-result-game.json")
      }

      "encode InlineQueryResultDocument to json" in {
        writeToString(inlineQueryResult13) should matchJsonResource("json/model/inline-query-result-document.json")
      }

      "encode InlineQueryResultGif to json" in {
        writeToString(inlineQueryResult14) should matchJsonResource("json/model/inline-query-result-gif.json")
      }

      "encode InlineQueryResultLocation to json" in {
        writeToString(inlineQueryResult15) should matchJsonResource("json/model/inline-query-result-location.json")
      }

      "encode InlineQueryResultMpeg4Gif to json" in {
        writeToString(inlineQueryResult16) should matchJsonResource("json/model/inline-query-result-mpeg-4-gif.json")
      }

      "encode InlineQueryResultPhoto to json" in {
        writeToString(inlineQueryResult17) should matchJsonResource("json/model/inline-query-result-photo.json")
      }

      "encode InlineQueryResultVenue to json" in {
        writeToString(inlineQueryResult18) should matchJsonResource("json/model/inline-query-result-venue.json")
      }

      "encode InlineQueryResultVideo to json" in {
        writeToString(inlineQueryResult19) should matchJsonResource("json/model/inline-query-result-video.json")
      }

      "encode InlineQueryResultVoice to json" in {
        writeToString(inlineQueryResult20) should matchJsonResource("json/model/inline-query-result-voice.json")
      }
    }
  }
}
