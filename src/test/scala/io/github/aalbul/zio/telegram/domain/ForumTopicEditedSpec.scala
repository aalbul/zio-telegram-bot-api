package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class ForumTopicEditedSpec extends BaseSpec {
  "ForumTopicEdited" when {
    "encoder" should {
      "encode forum topic edited to json" in {
        writeToString(forumTopicEdited1) should matchJsonResource("json/model/forum-topic-edited.json")
      }
    }

    "decoder" should {
      "decode forum topic edited from json" in {
        jsonResourceAs[ForumTopicEdited]("json/model/forum-topic-edited.json") shouldBe forumTopicEdited1
      }
    }
  }
}
