package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.domain.Markup.*
import io.github.aalbul.zio.telegram.test.BaseSpec

class MarkupSpec extends BaseSpec {
  "encoder" should {
    "encode inline keyboard markup to json" in {
      writeToString[Markup](inlineKeyboardMarkup1) should matchJsonResource("json/model/inline-keyboard-markup.json")
    }

    "encode reply keyboard markup to json" in {
      writeToString[Markup](replyKeyboardMarkup1) should matchJsonResource("json/model/reply-keyboard-markup.json")
    }

    "encode reply keyboard remove to json" in {
      writeToString[Markup](replyKeyboardRemove1) should matchJsonResource("json/model/reply-keyboard-remove.json")
    }

    "encode force reply to json" in {
      writeToString[Markup](forceReplyMarkup1) should matchJsonResource("json/model/force-reply.json")
    }
  }

  "decoder" should {
    "decode inline keyboard markup from json" in {
      jsonResourceAs[Markup]("json/model/inline-keyboard-markup.json") shouldBe inlineKeyboardMarkup1
    }

    "decode reply keyboard markup from json" in {
      jsonResourceAs[Markup]("json/model/reply-keyboard-markup.json") shouldBe replyKeyboardMarkup1
    }

    "decode reply keyboard remove from json" in {
      jsonResourceAs[Markup]("json/model/reply-keyboard-remove.json") shouldBe replyKeyboardRemove1
    }

    "decode force reply from json" in {
      jsonResourceAs[Markup]("json/model/force-reply.json") shouldBe forceReplyMarkup1
    }
  }
}
