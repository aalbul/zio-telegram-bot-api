package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatMemberBannedSpec extends BaseSpec {
  "ChatMemberBanned" when {
    "decoder" should {
      "decode ChatMemberBanned from json" in {
        jsonResourceAs[ChatMemberBanned]("json/model/chat-member-banned.json") shouldBe chatMember4
      }
    }
  }
}
