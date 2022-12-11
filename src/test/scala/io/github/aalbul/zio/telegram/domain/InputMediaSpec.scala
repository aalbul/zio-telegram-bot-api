package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec
import InputMedia.inputMediaEncoder

class InputMediaSpec extends BaseSpec {
  "InputMedia" when {
    "inputMediaEncoder" should {
      "encode InputMediaAnimation" in {
        inputMediaEncoder(inputMediaAnimation1) shouldBe jsonResource("json/model/input-media-animation.json")
      }

      "encode InputMediaAudio" in {
        inputMediaEncoder(inputMediaAudio1) shouldBe jsonResource("json/model/input-media-audio.json")
      }

      "encode InputMediaDocument" in {
        inputMediaEncoder(inputMediaDocument1) shouldBe jsonResource("json/model/input-media-document.json")
      }

      "encode InputMediaPhoto" in {
        inputMediaEncoder(inputMediaPhoto1) shouldBe jsonResource("json/model/input-media-photo.json")
      }

      "encode InputMediaVideo" in {
        inputMediaEncoder(inputMediaVideo1) shouldBe jsonResource("json/model/input-media-video.json")
      }
    }
  }
}
