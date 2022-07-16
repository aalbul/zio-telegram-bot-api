package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import AnimationMessage.animationMessageProjector

class AnimationMessageSpec extends BaseSpec {
  "AnimationMessage" when {
    "animationMessageProjector" should {
      "properly project animation messages" in {
        animationMessageProjector.project(animationMessage1) shouldBe Some(animationMessageProjection)
      }

      "not match any other messages" in {
        (allMessages - animationMessage1).flatMap(animationMessageProjector.project) shouldBe empty
      }
    }
  }
}
