package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.test.BaseSpec
import NewPollAnswer.*

class NewPollAnswerSpec extends BaseSpec {
  "NewPollAnswer" when {
    "newPollAnswerProjector" should {
      "properly handle new poll answer messages" in {
        newPollAnswerProjector.project(newPollAnswerMessage) shouldBe Some(newPollAnswerProjection)
      }

      "not match any other updates" in {
        (allUpdates - newPollAnswerMessage).flatMap(newPollAnswerProjector.project) shouldBe empty
      }
    }
  }
}
