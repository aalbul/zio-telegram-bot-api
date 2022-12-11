package io.github.aalbul.zio.telegram.domain

import io.circe.Json
import io.circe.syntax.EncoderOps
import io.github.aalbul.zio.telegram.domain.MessageEntityTypes.*
import io.github.aalbul.zio.telegram.test.BaseSpec

class MessageEntityTypesSpec extends BaseSpec {
  "MessageEntityTypes" when {
    "decoder" should {
      "decode message entity types from json" in {
        Json.fromString("bot_command").as[MessageEntityType] shouldBe Right(MessageEntityTypes.BotCommand)
        Json.fromString("text_mention").as[MessageEntityType] shouldBe Right(MessageEntityTypes.TextMention)
      }
    }

    "encoder" should {
      "encode message entity types to json" in {
        MessageEntityTypes.BotCommand.asJson shouldBe Json.fromString("bot_command")
        MessageEntityTypes.TextMention.asJson shouldBe Json.fromString("text_mention")
      }
    }
  }
}
