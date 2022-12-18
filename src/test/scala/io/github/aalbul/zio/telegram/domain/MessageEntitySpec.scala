package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class MessageEntitySpec extends BaseSpec {
  "MessageEntity" when {
    "encoder" should {
      "encode message entity to json" in {
        writeToString(messageEntity1) should matchJsonResource("json/model/message-entity.json")
      }
    }

    "decoder" should {
      "decode message entity from json" in {
        jsonResourceAs[MessageEntity]("json/model/message-entity.json") shouldBe messageEntity1
      }
    }
  }
}
