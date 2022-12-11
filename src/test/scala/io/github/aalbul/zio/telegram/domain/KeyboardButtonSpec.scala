package io.github.aalbul.zio.telegram.domain

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.test.BaseSpec

class KeyboardButtonSpec extends BaseSpec {
  "KeyboardButton" when {
    "encoder" should {
      "should encode keyboard button to json" in {
        keyboardButton1.asJson shouldBe jsonResource("json/model/keyboard-button.json")
      }
    }
  }
}
