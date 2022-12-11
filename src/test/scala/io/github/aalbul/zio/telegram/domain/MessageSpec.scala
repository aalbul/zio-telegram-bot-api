package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class MessageSpec extends BaseSpec {
  "Message" when {
    "decoder" should {
      "decode message from json" in {
        jsonResourceAs[Message]("json/model/full-message.json") shouldBe fullMessage1
      }
    }
  }
}
