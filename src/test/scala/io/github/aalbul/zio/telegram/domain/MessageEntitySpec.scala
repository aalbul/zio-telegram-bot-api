package io.github.aalbul.zio.telegram.domain

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.test.BaseSpec

class MessageEntitySpec extends BaseSpec {
  "MessageEntity" when {
    "encoder" should {
      "encode message entity to json" in {
        messageEntity1.asJson shouldBe jsonResource("json/model/message-entity.json")
      }
    }

    "decoder" should {
      "decode message entity json" in {
        jsonResourceAs[MessageEntity]("json/model/message-entity.json") shouldBe messageEntity1
      }
    }
  }
}
