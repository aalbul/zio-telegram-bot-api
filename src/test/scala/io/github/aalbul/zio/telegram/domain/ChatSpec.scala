package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatSpec extends BaseSpec {
  "Chat" when {
    "decoder" should {
      "should decode chat json" in {
        jsonResourceAs[Chat]("json/model/chat.json") shouldBe chat4
      }
    }
  }
}
