package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class ReplyKeyboardRemoveSpec extends BaseSpec {
  "ReplyKeyboardRemove" when {
    "encoder" should {
      "encode reply keyboard remove to json" in {
        writeToString(replyKeyboardRemove1) should matchJsonResource("json/model/reply-keyboard-remove.json")
      }
    }

    "decoder" should {
      "decode reply keyboard remove from json" in {
        jsonResourceAs[ReplyKeyboardRemove](
          "json/model/reply-keyboard-remove.json"
        ) shouldBe replyKeyboardRemove1
      }
    }
  }
}
