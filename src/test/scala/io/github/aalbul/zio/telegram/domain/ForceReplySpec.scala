package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class ForceReplySpec extends BaseSpec {
  "ForceReply" when {
    "encoder" should {
      "encode force reply to json" in {
        writeToString[ForceReply](forceReplyMarkup1) should matchJsonResource("json/model/force-reply.json")
      }
    }

    "decoder" should {
      "decode force reply from json" in {
        jsonResourceAs[ForceReply]("json/model/force-reply.json") shouldBe forceReplyMarkup1
      }
    }
  }
}
