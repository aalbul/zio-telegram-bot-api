package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import NewChatMembersMessage.newChatMembersProjector

class NewChatMembersMessageSpec extends BaseSpec {
  "NewChatMembersMessage" when {
    "newChatMembersProjector" should {
      "properly project new chat members messages" in {
        newChatMembersProjector.project(newChatMembersMessage1) shouldBe Some(newChatMembersProjection)
      }

      "not match any other messages" in {
        (allMessages - newChatMembersMessage1).flatMap(newChatMembersProjector.project) shouldBe empty
      }
    }
  }
}
