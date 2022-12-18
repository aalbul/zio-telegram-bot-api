package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class VoiceSpec extends BaseSpec {
  "Voice" when {
    "encoder" should {
      "encode voice to json" in {
        writeToString(voice1) should matchJsonResource("json/model/voice.json")
      }
    }

    "decoder" should {
      "decode voice from json" in {
        jsonResourceAs[Voice]("json/model/voice.json") shouldBe voice1
      }
    }
  }
}
