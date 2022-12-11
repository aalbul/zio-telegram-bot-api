package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatMemberUpdatedSpec extends BaseSpec {
  "ChatMemberUpdated" when {
    "decoder" should {
      "decode chat member updated from json" in {
        jsonResourceAs[ChatMemberUpdated]("json/model/chat-member-updated.json") shouldBe chatMemberUpdated1
      }
    }
  }
}
