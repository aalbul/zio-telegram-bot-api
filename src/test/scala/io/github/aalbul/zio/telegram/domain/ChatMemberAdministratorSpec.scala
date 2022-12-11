package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatMemberAdministratorSpec extends BaseSpec {
  "ChatMemberAdministrator" when {
    "decoder" should {
      "decode ChatMemberAdministrator from json" in {
        jsonResourceAs[ChatMemberAdministrator]("json/model/chat-member-administrator.json") shouldBe chatMember3
      }
    }
  }
}
