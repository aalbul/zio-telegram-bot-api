package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.projection.message.GeneralForumTopicHiddenMessage.generalForumTopicHiddenMessageProjector
import io.github.aalbul.zio.telegram.test.BaseSpec

class GeneralForumTopicHiddenMessageSpec extends BaseSpec {
  "GeneralForumTopicHiddenMessage" when {
    "generalForumTopicHiddenMessageProjector" should {
      "properly project general forum topic hidden message projector messages" in {
        generalForumTopicHiddenMessageProjector.project(generalForumTopicHiddenMessage1) shouldBe Some(
          generalForumTopicHiddenMessageProjection
        )
      }

      "not match any other messages" in {
        (allMessages - generalForumTopicHiddenMessage1).flatMap(
          generalForumTopicHiddenMessageProjector.project
        ) shouldBe empty
      }
    }
  }
}
