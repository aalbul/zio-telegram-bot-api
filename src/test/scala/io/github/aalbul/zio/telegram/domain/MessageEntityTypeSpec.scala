package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.{readFromString, writeToString}
import io.github.aalbul.zio.telegram.domain.MessageEntityType.*
import io.github.aalbul.zio.telegram.test.BaseSpec

class MessageEntityTypeSpec extends BaseSpec {
  "MessageEntityType" when {
    "encoder" should {
      "encode message entity types to json" in {
        writeToString[MessageEntityType](MessageEntityType.BotCommand) shouldBe "\"bot_command\""
        writeToString[MessageEntityType](MessageEntityType.TextMention) shouldBe "\"text_mention\""
      }
    }

    "decoder" should {
      "decode message entity types from json" in {
        readFromString[MessageEntityType]("\"bot_command\"") shouldBe MessageEntityType.BotCommand
        readFromString[MessageEntityType]("\"text_mention\"") shouldBe MessageEntityType.TextMention
      }
    }
  }
}
