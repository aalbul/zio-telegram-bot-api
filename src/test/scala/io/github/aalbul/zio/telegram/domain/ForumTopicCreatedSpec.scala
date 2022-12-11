package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class ForumTopicCreatedSpec extends BaseSpec {
  "ForumTopicCreated" when {
    "decoder" should {
      "should forum topic created json" in {
        jsonResourceAs[ForumTopicCreated]("json/model/forum-topic-created.json") shouldBe forumTopicCreated1
      }
    }
  }
}
