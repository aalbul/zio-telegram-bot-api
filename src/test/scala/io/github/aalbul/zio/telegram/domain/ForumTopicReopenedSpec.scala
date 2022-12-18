package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class ForumTopicReopenedSpec extends BaseSpec {
  "ForumTopicReopened" when {
    "encoder" should {
      "encode forum topic reopened to json" in {
        writeToString[ForumTopicReopened](forumTopicReopened1) should matchJsonResource(
          "json/model/forum-topic-reopened.json"
        )
      }
    }

    "decoder" should {
      "decode forum topic reopened from json" in {
        jsonResourceAs[ForumTopicReopened]("json/model/forum-topic-reopened.json") shouldBe forumTopicReopened1
      }
    }
  }
}
