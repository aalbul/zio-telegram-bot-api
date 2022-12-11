package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatLocationSpec extends BaseSpec {
  "ChatLocation" when {
    "decoder" should {
      "should decode chat location json" in {
        jsonResourceAs[ChatLocation]("json/model/chat-location.json") shouldBe chatLocation1
      }
    }
  }
}
