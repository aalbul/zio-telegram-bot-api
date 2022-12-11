package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatMemberSpec extends BaseSpec {
  "ChatMember" when {
    "decoder" should {
      "should decode ChatMemberOwner from json" in {
        jsonResourceAs[ChatMember]("json/model/chat-member-owner.json") shouldBe chatMember1
      }

      "should decode ChatMemberAdministrator from json" in {
        jsonResourceAs[ChatMember]("json/model/chat-member-administrator.json") shouldBe chatMember3
      }

      "should decode ChatMemberBanned from json" in {
        jsonResourceAs[ChatMember]("json/model/chat-member-banned.json") shouldBe chatMember4
      }

      "should decode ChatMemberLeft from json" in {
        jsonResourceAs[ChatMember]("json/model/chat-member-left.json") shouldBe chatMember2
      }

      "should decode ChatMemberMember from json" in {
        jsonResourceAs[ChatMember]("json/model/chat-member-member.json") shouldBe chatMember5
      }

      "should decode ChatMemberRestricted from json" in {
        jsonResourceAs[ChatMember]("json/model/chat-member-restricted.json") shouldBe chatMember6
      }
    }
  }
}
