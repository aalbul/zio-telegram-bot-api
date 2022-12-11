package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.projection.message.ForumTopicCreatedMessage.forumTopicCreatedProjector
import io.github.aalbul.zio.telegram.test.BaseSpec

class ForumTopicCreatedMessageSpec extends BaseSpec {
  "ForumTopicCreatedMessage" when {
    "forumTopicCreatedProjector" should {
      "properly project forum topic created messages" in {
        forumTopicCreatedProjector.project(forumTopicCreatedMessage1) shouldBe Some(forumTopicCreatedProjection)
      }

      "not match any other messages" in {
        (allMessages - forumTopicCreatedMessage1).flatMap(forumTopicCreatedProjector.project) shouldBe empty
      }
    }
  }
}
