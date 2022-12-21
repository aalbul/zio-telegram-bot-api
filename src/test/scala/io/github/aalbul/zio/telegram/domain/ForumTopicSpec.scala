package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class ForumTopicSpec extends BaseSpec {
  "ForumTopic" when {
    "encode" should {
      "encode forum topic to json" in {
        writeToString(forumTopic1) should matchJsonResource("json/model/forum-topic.json")
      }
    }

    "decoder" should {
      "decode forum topic from json" in {
        jsonResourceAs[ForumTopic]("json/model/forum-topic.json") shouldBe forumTopic1
      }
    }
  }
}
