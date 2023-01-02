package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.projection.message.GeneralForumTopicUnhiddenMessage.generalForumTopicUnhiddenMessageProjector
import io.github.aalbul.zio.telegram.test.BaseSpec

class GeneralForumTopicUnhiddenMessageSpec extends BaseSpec {
  "GeneralForumTopicUnhiddenMessage" when {
    "generalForumTopicUnhiddenMessageProjector" should {
      "properly project general forum topic unhidden message projector messages" in {
        generalForumTopicUnhiddenMessageProjector.project(generalForumTopicUnhiddenMessage1) shouldBe Some(
          generalForumTopicUnhiddenMessageProjection
        )
      }

      "not match any other messages" in {
        (allMessages - generalForumTopicUnhiddenMessage1).flatMap(
          generalForumTopicUnhiddenMessageProjector.project
        ) shouldBe empty
      }
    }
  }
}
