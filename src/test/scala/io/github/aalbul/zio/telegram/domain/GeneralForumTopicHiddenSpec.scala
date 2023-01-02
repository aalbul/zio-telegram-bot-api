package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class GeneralForumTopicHiddenSpec extends BaseSpec {
  "GeneralForumTopicHidden" when {
    "encoder" should {
      "encode general forum topic hidden to json" in {
        writeToString(generalForumTopicHidden1) should matchJsonResource("json/model/general-forum-topic-hidden.json")
      }
    }

    "decoder" should {
      "decode general forum topic hidden from json" in {
        jsonResourceAs[GeneralForumTopicHidden](
          "json/model/general-forum-topic-hidden.json"
        ) shouldBe generalForumTopicHidden1
      }
    }
  }
}
