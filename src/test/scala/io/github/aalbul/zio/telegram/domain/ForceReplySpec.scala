package io.github.aalbul.zio.telegram.domain

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.test.BaseSpec

class ForceReplySpec extends BaseSpec {
  "ForceReply" when {
    "encoder" should {
      "should encode force to json" in {
        forceReplyMarkup1.asJson shouldBe jsonResource("json/model/force-reply.json")
      }
    }
  }
}
