package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatInviteLinkSpec extends BaseSpec {
  "ChatInviteLink" when {
    "encoder" should {
      "encode chat invite links to json" in {
        writeToString(chatInviteLink1) should matchJsonResource("json/model/chat-invite-link.json")
      }
    }

    "decoder" should {
      "decode chat invite links from json" in {
        jsonResourceAs[ChatInviteLink]("json/model/chat-invite-link.json") shouldBe chatInviteLink1
      }
    }
  }
}
