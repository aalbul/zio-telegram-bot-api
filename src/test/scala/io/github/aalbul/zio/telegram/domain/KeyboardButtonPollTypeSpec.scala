package io.github.aalbul.zio.telegram.domain

import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.test.BaseSpec

class KeyboardButtonPollTypeSpec extends BaseSpec {
  "KeyboardButtonPollType" when {
    "encoder" should {
      "should encode keyboard button poll type to json" in {
        keyboardButtonPollType1.asJson shouldBe jsonResource("json/model/keyboard-button-poll-type.json")
      }
    }
  }
}
