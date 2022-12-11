package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class ForumTopicReopenedSpec extends BaseSpec {
  "ForumTopicReopened" when {
    "decoder" should {
      "should forum topic reopened json" in {
        jsonResourceAs[ForumTopicReopened]("json/model/forum-topic-reopened.json") shouldBe forumTopicReopened1
      }
    }
  }
}
