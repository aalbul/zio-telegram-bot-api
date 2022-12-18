package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class PollSpec extends BaseSpec {
  "Poll" when {
    "encoder" should {
      "encode poll to json" in {
        writeToString(poll1) should matchJsonResource("json/model/poll.json")
      }
    }

    "decoder" should {
      "decode poll from json" in {
        jsonResourceAs[Poll]("json/model/poll.json") shouldBe poll1
      }
    }
  }
}
