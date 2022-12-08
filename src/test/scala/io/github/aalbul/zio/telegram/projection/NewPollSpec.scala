package io.github.aalbul.zio.telegram.projection

import io.github.aalbul.zio.telegram.test.BaseSpec
import NewPoll.*

class NewPollSpec extends BaseSpec {
  "NewPoll" when {
    "newPollProjector" should {
      "properly handle new poll messages" in {
        newPollProjector.project(newPollMessage) shouldBe Some(newPollProjection)
      }

      "not match any other updates" in {
        (allUpdates - newPollMessage).flatMap(newPollProjector.project) shouldBe empty
      }
    }
  }
}
