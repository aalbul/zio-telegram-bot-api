package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import NewChatTitleMessage.newChatTitleProjector

class NewChatTitleMessageSpec extends BaseSpec {
  "NewChatTitleMessage" when {
    "newChatTitleProjector" should {
      "properly project new chat title messages" in {
        newChatTitleProjector.project(newChatTitleMessage1) shouldBe Some(newChatTitleProjection)
      }

      "not match any other messages" in {
        (allMessages - newChatTitleMessage1).flatMap(newChatTitleProjector.project) shouldBe empty
      }
    }
  }
}
