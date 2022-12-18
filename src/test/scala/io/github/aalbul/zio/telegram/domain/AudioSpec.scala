package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class AudioSpec extends BaseSpec {
  "Audio" when {
    "encoder" should {
      "encode audio to json" in {
        writeToString(audio1) should matchJsonResource("json/model/audio.json")
      }
    }

    "decoder" should {
      "decode audio from json" in {
        jsonResourceAs[Audio]("json/model/audio.json") shouldBe audio1
      }
    }
  }
}
