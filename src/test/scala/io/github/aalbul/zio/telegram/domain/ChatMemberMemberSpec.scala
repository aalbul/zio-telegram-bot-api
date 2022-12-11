package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatMemberMemberSpec extends BaseSpec {
  "ChatMemberMember" when {
    "decoder" should {
      "decode chat member member from json" in {
        jsonResourceAs[ChatMemberMember]("json/model/chat-member-member.json") shouldBe chatMember5
      }
    }
  }
}
