package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatMemberRestrictedSpec extends BaseSpec {
  "ChatMemberRestricted" when {
    "decoder" should {
      "decode chat member restricted from json" in {
        jsonResourceAs[ChatMemberRestricted]("json/model/chat-member-restricted.json") shouldBe chatMember6
      }
    }
  }
}
