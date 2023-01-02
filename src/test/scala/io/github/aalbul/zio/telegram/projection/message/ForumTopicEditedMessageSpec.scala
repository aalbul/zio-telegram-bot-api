package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.projection.message.ForumTopicEditedMessage.forumTopicEditedProjector
import io.github.aalbul.zio.telegram.test.BaseSpec

class ForumTopicEditedMessageSpec extends BaseSpec {
  "ForumTopicEditedMessage" when {
    "forumTopicEditedProjector" should {
      "properly project forum topic edited messages" in {
        forumTopicEditedProjector.project(forumTopicEditedMessage1) shouldBe Some(forumTopicEditedProjection)
      }

      "not match any other messages" in {
        (allMessages - forumTopicEditedMessage1).flatMap(forumTopicEditedProjector.project) shouldBe empty
      }
    }
  }
}
