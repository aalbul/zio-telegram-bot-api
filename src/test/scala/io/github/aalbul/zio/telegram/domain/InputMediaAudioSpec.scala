package io.github.aalbul.zio.telegram.domain

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.test.BaseSpec

class InputMediaAudioSpec extends BaseSpec {
  "InputMediaAudio" when {
    "encoder" should {
      "should encode input media audio to json" in {
        inputMediaAudio1.asJson shouldBe jsonResource("json/model/input-media-audio.json")
      }
    }
  }
}
