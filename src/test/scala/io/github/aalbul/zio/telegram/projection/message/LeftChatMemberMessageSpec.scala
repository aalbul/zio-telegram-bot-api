package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import LeftChatMemberMessage.leftChatMemberMessageProjector

class LeftChatMemberMessageSpec extends BaseSpec {
  "LeftChatMemberMessage" when {
    "leftChatMemberMessageProjector" should {
      "properly project left chat member messages" in {
        leftChatMemberMessageProjector.project(leftChatMemberMessage1) shouldBe Some(leftChatMemberProjection)
      }

      "not match any other messages" in {
        (allMessages - leftChatMemberMessage1).flatMap(leftChatMemberMessageProjector.project) shouldBe empty
      }
    }
  }
}
