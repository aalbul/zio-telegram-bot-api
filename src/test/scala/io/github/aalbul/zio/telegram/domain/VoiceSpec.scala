package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class VoiceSpec extends BaseSpec {
  "Voice" when {
    "decoder" should {
      "decode voice from json" in {
        jsonResourceAs[Voice]("json/model/voice.json") shouldBe voice1
      }
    }
  }
}
