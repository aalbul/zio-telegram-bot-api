package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class ChatSpec extends BaseSpec {
  "Chat" when {
    "encode" should {
      "encode chat to json" in {
        writeToString(chat4) should matchJsonResource("json/model/chat.json")
      }
    }

    "decoder" should {
      "decode chat from json" in {
        jsonResourceAs[Chat]("json/model/chat.json") shouldBe chat4
      }
    }
  }
}
