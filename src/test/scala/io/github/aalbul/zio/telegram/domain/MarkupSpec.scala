package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.domain.Markup.*
import io.github.aalbul.zio.telegram.test.BaseSpec

class MarkupSpec extends BaseSpec {
  "encoder" should {
    "encode inline keyboard markup to json" in {
      markupEncoder(inlineKeyboardMarkup1) shouldBe jsonResource("json/model/inline-keyboard-markup.json")
    }

    "encode reply keyboard markup to json" in {
      markupEncoder(replyKeyboardMarkup1) shouldBe jsonResource("json/model/reply-keyboard-markup.json")
    }

    "encode reply keyboard remove to json" in {
      markupEncoder(replyKeyboardRemove1) shouldBe jsonResource("json/model/reply-keyboard-remove.json")
    }

    "encode force reply to json" in {
      markupEncoder(forceReplyMarkup1) shouldBe jsonResource("json/model/force-reply.json")
    }
  }
}
