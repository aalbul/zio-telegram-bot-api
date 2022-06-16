package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec
import InputMedia.inputMediaEncoder

class InputMediaSpec extends BaseSpec {
  "InputMedia" when {
    "inputMediaEncoder" should {
      "encode InputMediaAnimation" in {
        inputMediaEncoder(inputMediaAnimation) shouldBe jsonResource("json/entity/input-media-animation.json")
      }

      "encode InputMediaAudio" in {
        inputMediaEncoder(inputMediaAudio) shouldBe jsonResource("json/entity/input-media-audio.json")
      }

      "encode InputMediaDocument" in {
        inputMediaEncoder(inputMediaDocument) shouldBe jsonResource("json/entity/input-media-document.json")
      }

      "encode InputMediaPhoto" in {
        inputMediaEncoder(inputMediaPhoto) shouldBe jsonResource("json/entity/input-media-photo.json")
      }

      "encode InputMediaVideo" in {
        inputMediaEncoder(inputMediaVideo) shouldBe jsonResource("json/entity/input-media-video.json")
      }
    }
  }
}
