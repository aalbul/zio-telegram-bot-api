package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class InlineKeyboardButtonSpec extends BaseSpec {
  "InlineKeyboardButton" when {
    "encoder" should {
      "encode inline keyboard button to json" in {
        writeToString(inlineKeyboardButton1) should matchJsonResource("json/model/inline-keyboard-button.json")
      }
    }

    "decoder" should {
      "decode inline keyboard button from json" in {
        jsonResourceAs[InlineKeyboardButton](
          "json/model/inline-keyboard-button.json"
        ) shouldBe inlineKeyboardButton1
      }
    }
  }
}
