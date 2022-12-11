package io.github.aalbul.zio.telegram.domain

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.test.BaseSpec

class InlineKeyboardMarkupSpec extends BaseSpec {
  "InlineKeyboardMarkup" when {
    "encoder" should {
      "should encode inline keyboard markup to json" in {
        inlineKeyboardMarkup1.asJson shouldBe jsonResource("json/model/inline-keyboard-markup.json")
      }
    }

    "decoder" should {
      "should decode inline keyboard markup json" in {
        jsonResourceAs[InlineKeyboardMarkup]("json/model/inline-keyboard-markup.json") shouldBe inlineKeyboardMarkup1
      }
    }
  }
}
