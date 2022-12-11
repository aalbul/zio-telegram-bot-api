package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatJoinRequestSpec extends BaseSpec {
  "ChatJoinRequest" when {
    "decoder" should {
      "should decode chat join request json" in {
        jsonResourceAs[ChatJoinRequest]("json/model/chat-join-request.json") shouldBe chatJoinRequest1
      }
    }
  }
}
