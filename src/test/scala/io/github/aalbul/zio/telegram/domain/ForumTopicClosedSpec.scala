package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class ForumTopicClosedSpec extends BaseSpec {
  "ForumTopicClosed" when {
    "decoder" should {
      "should forum topic closed json" in {
        jsonResourceAs[ForumTopicClosed]("json/model/forum-topic-closed.json") shouldBe forumTopicClosed1
      }
    }
  }
}
