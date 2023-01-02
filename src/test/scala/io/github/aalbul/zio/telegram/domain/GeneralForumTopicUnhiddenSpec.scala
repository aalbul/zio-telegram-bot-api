package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class GeneralForumTopicUnhiddenSpec extends BaseSpec {
  "GeneralForumTopicUnhidden" when {
    "encoder" should {
      "encode general forum topic unhidden to json" in {
        writeToString(generalForumTopicUnhidden1) should matchJsonResource("json/model/general-forum-topic-unhidden.json")
      }
    }

    "decoder" should {
      "decode general forum topic unhidden from json" in {
        jsonResourceAs[GeneralForumTopicUnhidden](
          "json/model/general-forum-topic-unhidden.json"
        ) shouldBe generalForumTopicUnhidden1
      }
    }
  }
}
