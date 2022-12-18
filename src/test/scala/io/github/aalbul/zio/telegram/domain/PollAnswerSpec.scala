package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class PollAnswerSpec extends BaseSpec {
  "PollAnswer" when {
    "encoder" should {
      "encode poll answer to json" in {
        writeToString(pollAnswer1) should matchJsonResource("json/model/poll-answer.json")
      }
    }

    "decoder" should {
      "decode poll answer from json" in {
        jsonResourceAs[PollAnswer]("json/model/poll-answer.json") shouldBe pollAnswer1
      }
    }
  }
}
