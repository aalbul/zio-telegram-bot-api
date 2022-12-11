package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.projection.message.ForumTopicClosedMessage.forumTopicClosedProjector
import io.github.aalbul.zio.telegram.test.BaseSpec

class ForumTopicClosedMessageSpec extends BaseSpec {
  "ForumTopicClosedMessage" when {
    "forumTopicClosedProjector" should {
      "properly project forum topic closed messages" in {
        forumTopicClosedProjector.project(forumTopicClosedMessage1) shouldBe Some(forumTopicClosedProjection)
      }

      "not match any other messages" in {
        (allMessages - forumTopicClosedMessage1).flatMap(forumTopicClosedProjector.project) shouldBe empty
      }
    }
  }
}
