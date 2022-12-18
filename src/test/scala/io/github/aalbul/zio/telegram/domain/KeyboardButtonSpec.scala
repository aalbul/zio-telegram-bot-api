package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class KeyboardButtonSpec extends BaseSpec {
  "KeyboardButton" when {
    "encoder" should {
      "encode keyboard button to json" in {
        writeToString(keyboardButton1) should matchJsonResource("json/model/keyboard-button.json")
      }
    }

    "decoder" should {
      "decode keyboard button from json" in {
        jsonResourceAs[KeyboardButton]("json/model/keyboard-button.json") shouldBe keyboardButton1
      }
    }
  }
}
