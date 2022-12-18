package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatMemberSpec extends BaseSpec {
  "ChatMember" when {
    "encoder" should {
      "should encode ChatMemberOwner to json" in {
        writeToString(chatMember1) should matchJsonResource("json/model/chat-member-owner.json")
      }

      "should decode ChatMemberAdministrator from json" in {
        writeToString(chatMember3) should matchJsonResource("json/model/chat-member-administrator.json")
      }

      "should decode ChatMemberBanned from json" in {
        writeToString(chatMember4) should matchJsonResource("json/model/chat-member-banned.json")
      }

      "should decode ChatMemberLeft from json" in {
        writeToString(chatMember2) should matchJsonResource("json/model/chat-member-left.json")
      }

      "should decode ChatMemberMember from json" in {
        writeToString(chatMember5) should matchJsonResource("json/model/chat-member-member.json")
      }

      "should decode ChatMemberRestricted from json" in {
        writeToString(chatMember6) should matchJsonResource("json/model/chat-member-restricted.json")
      }
    }

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
