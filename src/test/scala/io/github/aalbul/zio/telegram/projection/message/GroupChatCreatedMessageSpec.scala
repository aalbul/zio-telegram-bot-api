package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import GroupChatCreatedMessage.groupChatCreatedProjector

class GroupChatCreatedMessageSpec extends BaseSpec {
  "GroupChatCreatedMessage" when {
    "groupChatCreatedProjector" should {
      "properly project group chat created messages" in {
        groupChatCreatedProjector.project(groupChatCreatedMessage1) shouldBe Some(groupChatCreatedProjection)
      }

      "not match any other messages" in {
        (allMessages - groupChatCreatedMessage1).flatMap(groupChatCreatedProjector.project) shouldBe empty
      }
    }
  }
}
