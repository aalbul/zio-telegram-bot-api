package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class MessageSpec extends BaseSpec {
  "Message" when {
    "encode" should {
      "encode message to json" in {
        writeToString(fullMessage1) should matchJsonResource("json/model/full-message.json")
      }
    }

    "decoder" should {
      "decode message from json" in {
        jsonResourceAs[Message]("json/model/full-message.json") shouldBe fullMessage1
      }
    }
  }
}
