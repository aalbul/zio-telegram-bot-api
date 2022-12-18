package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class InputMediaSpec extends BaseSpec {
  "InputMedia" when {
    "encoder" should {
      "encode InputMediaAnimation to json" in {
        writeToString(inputMediaAnimation1) should matchJsonResource("json/model/input-media-animation.json")
      }

      "encode InputMediaAudio to json" in {
        writeToString(inputMediaAudio1) should matchJsonResource("json/model/input-media-audio.json")
      }

      "encode InputMediaDocument to json" in {
        writeToString(inputMediaDocument1) should matchJsonResource("json/model/input-media-document.json")
      }

      "encode InputMediaPhoto to json" in {
        writeToString(inputMediaPhoto1) should matchJsonResource("json/model/input-media-photo.json")
      }

      "encode InputMediaVideo to json" in {
        writeToString(inputMediaVideo1) should matchJsonResource("json/model/input-media-video.json")
      }
    }

    "decoder" should {
      "decode InputMediaAnimation from json" in {
        jsonResourceAs[InputMedia]("json/model/input-media-animation.json") shouldBe inputMediaAnimation1
      }

      "decode InputMediaAudio from json" in {
        jsonResourceAs[InputMedia]("json/model/input-media-audio.json") shouldBe inputMediaAudio1
      }

      "decode InputMediaDocument from json" in {
        jsonResourceAs[InputMedia]("json/model/input-media-document.json") shouldBe inputMediaDocument1
      }

      "decode InputMediaPhoto from json" in {
        jsonResourceAs[InputMedia]("json/model/input-media-photo.json") shouldBe inputMediaPhoto1
      }

      "decode InputMediaVideo from json" in {
        jsonResourceAs[InputMedia]("json/model/input-media-video.json") shouldBe inputMediaVideo1
      }
    }
  }
}
