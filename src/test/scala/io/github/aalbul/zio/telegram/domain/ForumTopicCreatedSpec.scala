package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class ForumTopicCreatedSpec extends BaseSpec {
  "ForumTopicCreated" when {
    "encoder" should {
      "encode forum topic created to json" in {
        writeToString[ForumTopicCreated](forumTopicCreated1) should matchJsonResource(
          "json/model/forum-topic-created.json"
        )
      }
    }

    "decoder" should {
      "decode forum topic created from json" in {
        jsonResourceAs[ForumTopicCreated]("json/model/forum-topic-created.json") shouldBe forumTopicCreated1
      }
    }
  }
}
