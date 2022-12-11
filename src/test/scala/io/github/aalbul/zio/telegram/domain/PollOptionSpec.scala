package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class PollOptionSpec extends BaseSpec {
  "PollOption" when {
    "decoder" should {
      "decode poll option from json" in {
        jsonResourceAs[PollOption]("json/model/poll-option.json") shouldBe pollOption1
      }
    }
  }
}
