package io.github.aalbul.zio.telegram.domain

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.test.BaseSpec

class ReplyKeyboardRemoveSpec extends BaseSpec {
  "ReplyKeyboardRemove" when {
    "encoder" should {
      "encode reply keyboard remove to json" in {
        replyKeyboardRemove1.asJson shouldBe jsonResource("json/model/reply-keyboard-remove.json")
      }
    }
  }
}
