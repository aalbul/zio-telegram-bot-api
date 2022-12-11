package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class MessageIdSpec extends BaseSpec {
  "MessageId" when {
    "decoder" should {
      "decode message id from json" in {
        jsonResourceAs[MessageId]("json/model/message-id.json") shouldBe messageId1
      }
    }
  }
}
