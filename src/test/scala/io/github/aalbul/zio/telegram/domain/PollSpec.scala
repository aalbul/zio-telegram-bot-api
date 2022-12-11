package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class PollSpec extends BaseSpec {
  "Poll" when {
    "decoder" should {
      "decode poll from json" in {
        jsonResourceAs[Poll]("json/model/poll.json") shouldBe poll1
      }
    }
  }
}
