package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class PollAnswerSpec extends BaseSpec {
  "PollAnswer" when {
    "decoder" should {
      "decode poll answer from json" in {
        jsonResourceAs[PollAnswer]("json/model/poll-answer.json") shouldBe pollAnswer1
      }
    }
  }
}
