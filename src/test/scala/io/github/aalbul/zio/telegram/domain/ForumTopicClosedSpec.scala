package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class ForumTopicClosedSpec extends BaseSpec {
  "ForumTopicClosed" when {
    "encoder" should {
      "encode forum topic closed to json" in {
        writeToString[ForumTopicClosed](forumTopicClosed1) should matchJsonResource(
          "json/model/forum-topic-closed.json"
        )
      }
    }

    "decoder" should {
      "decode forum topic closed from json" in {
        jsonResourceAs[ForumTopicClosed]("json/model/forum-topic-closed.json") shouldBe forumTopicClosed1
      }
    }
  }
}
