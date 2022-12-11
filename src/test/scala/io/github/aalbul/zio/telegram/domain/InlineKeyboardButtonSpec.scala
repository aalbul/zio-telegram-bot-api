package io.github.aalbul.zio.telegram.domain

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.test.BaseSpec

class InlineKeyboardButtonSpec extends BaseSpec {
  "InlineKeyboardButton" when {
    "encoder" should {
      "should encode inline keyboard button to json" in {
        inlineKeyboardButton1.asJson shouldBe jsonResource("json/model/inline-keyboard-button.json")
      }
    }

    "decoder" should {
      "should decode inline keyboard button json" in {
        jsonResourceAs[InlineKeyboardButton]("json/model/inline-keyboard-button.json") shouldBe inlineKeyboardButton1
      }
    }
  }
}
