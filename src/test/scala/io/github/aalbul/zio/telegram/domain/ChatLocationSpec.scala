package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatLocationSpec extends BaseSpec {
  "ChatLocation" when {
    "encoder" should {
      "encode chat location to json" in {
        writeToString(chatLocation1) should matchJsonResource("json/model/chat-location.json")
      }
    }

    "decoder" should {
      "decode chat location from json" in {
        jsonResourceAs[ChatLocation]("json/model/chat-location.json") shouldBe chatLocation1
      }
    }
  }
}
