package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class MessageIdSpec extends BaseSpec {
  "MessageId" when {
    "encoder" should {
      "encode message id to json" in {
        writeToString(messageId1) should matchJsonResource("json/model/message-id.json")
      }
    }

    "decoder" should {
      "decode message id from json" in {
        jsonResourceAs[MessageId]("json/model/message-id.json") shouldBe messageId1
      }
    }
  }
}
