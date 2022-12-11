package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatInviteLinkSpec extends BaseSpec {
  "ChatInviteLink" when {
    "decoder" should {
      "should decode chat invite links json" in {
        jsonResourceAs[ChatInviteLink]("json/model/chat-invite-link.json") shouldBe chatInviteLink1
      }
    }
  }
}
