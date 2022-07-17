package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import SupergroupChatCreatedMessage.supergroupChatCreatedProjector

class SupergroupChatCreatedMessageSpec extends BaseSpec {
  "SupergroupChatCreatedMessage" when {
    "supergroupChatCreatedProjector" should {
      "properly project supergroup chat created messages" in {
        supergroupChatCreatedProjector.project(supergroupChatCreatedMessage1) shouldBe Some(supergroupChatCreatedProjection)
      }

      "not match any other messages" in {
        (allMessages - supergroupChatCreatedMessage1).flatMap(supergroupChatCreatedProjector.project) shouldBe empty
      }
    }
  }
}
