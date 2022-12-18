package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class InlineKeyboardMarkupSpec extends BaseSpec {
  "InlineKeyboardMarkup" when {
    "encoder" should {
      "encode inline keyboard markup to json" in {
        writeToString(inlineKeyboardMarkup1) should matchJsonResource("json/model/inline-keyboard-markup.json")
      }
    }

    "decoder" should {
      "decode inline keyboard markup from json" in {
        jsonResourceAs[InlineKeyboardMarkup](
          "json/model/inline-keyboard-markup.json"
        ) shouldBe inlineKeyboardMarkup1
      }
    }
  }
}
