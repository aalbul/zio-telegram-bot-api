package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatMemberOwnerSpec extends BaseSpec {
  "ChatMemberOwner" when {
    "decoder" should {
      "decode chat member owner from json" in {
        jsonResourceAs[ChatMemberOwner]("json/model/chat-member-owner.json") shouldBe chatMember1
      }
    }
  }
}
