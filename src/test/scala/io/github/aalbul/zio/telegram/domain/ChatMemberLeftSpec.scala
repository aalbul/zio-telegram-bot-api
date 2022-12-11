package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatMemberLeftSpec extends BaseSpec {
  "ChatMemberLeft" when {
    "decoder" should {
      "decode chat member left from json" in {
        jsonResourceAs[ChatMemberLeft]("json/model/chat-member-left.json") shouldBe chatMember2
      }
    }
  }
}
