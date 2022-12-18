package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class PollOptionSpec extends BaseSpec {
  "PollOption" when {
    "encoder" should {
      "encode poll option to json" in {
        writeToString(pollOption1) should matchJsonResource("json/model/poll-option.json")
      }
    }

    "decoder" should {
      "decode poll option from json" in {
        jsonResourceAs[PollOption]("json/model/poll-option.json") shouldBe pollOption1
      }
    }
  }
}
