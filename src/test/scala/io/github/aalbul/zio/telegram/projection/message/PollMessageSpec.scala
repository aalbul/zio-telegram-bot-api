package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import PollMessage.pollMessageProjector

class PollMessageSpec extends BaseSpec {
  "PollMessage" when {
    "pollMessageProjector" should {
      "properly project poll messages" in {
        pollMessageProjector.project(pollMessage1) shouldBe Some(pollMessageProjection)
      }

      "not match any other messages" in {
        (allMessages - pollMessage1).flatMap(pollMessageProjector.project) shouldBe empty
      }
    }
  }
}
