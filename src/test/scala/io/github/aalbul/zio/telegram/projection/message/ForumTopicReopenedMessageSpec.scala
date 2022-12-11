package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.projection.message.ForumTopicReopenedMessage.forumTopicReopenedProjector
import io.github.aalbul.zio.telegram.test.BaseSpec

class ForumTopicReopenedMessageSpec extends BaseSpec {
  "ForumTopicReopenedMessage" when {
    "forumTopicReopenedProjector" should {
      "properly project forum topic reopened messages" in {
        forumTopicReopenedProjector.project(forumTopicReopenedMessage1) shouldBe Some(forumTopicReopenedProjection)
      }

      "not match any other messages" in {
        (allMessages - forumTopicReopenedMessage1).flatMap(forumTopicReopenedProjector.project) shouldBe empty
      }
    }
  }
}
