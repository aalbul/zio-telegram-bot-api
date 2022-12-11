package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class AudioSpec extends BaseSpec {
  "Audio" when {
    "decoder" should {
      "decode audio json" in {
        jsonResourceAs[Audio]("json/model/audio.json") shouldBe audio1
      }
    }
  }
}
