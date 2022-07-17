package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import PinnedMessageMessage.pinnedMessageMessageProjector

class PinnedMessageMessageSpec extends BaseSpec {
  "PinnedMessageMessage" when {
    "pinnedMessageMessageProjector" should {
      "properly project pinned message messages" in {
        pinnedMessageMessageProjector.project(pinnedMessageMessage1) shouldBe Some(pinnedMessageProjection)
      }

      "not match any other messages" in {
        (allMessages - pinnedMessageMessage1).flatMap(pinnedMessageMessageProjector.project) shouldBe empty
      }
    }
  }
}
