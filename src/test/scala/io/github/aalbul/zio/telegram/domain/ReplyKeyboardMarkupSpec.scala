package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class ReplyKeyboardMarkupSpec extends BaseSpec {
  "ReplyKeyboardMarkup" when {
    "encoder" should {
      "encode reply keyboard markup to json" in {
        writeToString(replyKeyboardMarkup1) should matchJsonResource("json/model/reply-keyboard-markup.json")
      }
    }

    "decoder" should {
      "decode reply keyboard markup from json" in {
        jsonResourceAs[ReplyKeyboardMarkup](
          "json/model/reply-keyboard-markup.json"
        ) shouldBe replyKeyboardMarkup1
      }
    }
  }
}
