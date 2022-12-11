package io.github.aalbul.zio.telegram.domain

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.test.BaseSpec

class ReplyKeyboardMarkupSpec extends BaseSpec {
  "ReplyKeyboardMarkup" when {
    "encoder" should {
      "encode reply keyboard markup to json" in {
        replyKeyboardMarkup1.asJson shouldBe jsonResource("json/model/reply-keyboard-markup.json")
      }
    }
  }
}
