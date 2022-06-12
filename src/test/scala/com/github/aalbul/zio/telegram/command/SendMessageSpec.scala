package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.command.SendMessage.SendMessagePayload
import com.github.aalbul.zio.telegram.domain.{Message, ParseModes}
import com.github.aalbul.zio.telegram.test.BaseSpec
import io.circe.syntax.EncoderOps

class SendMessageSpec extends BaseSpec {
  trait Scope {
    val command: Command[Message] =
      SendMessage
        .of(chatId = "331", text = "hello world")
        .withParseMode(ParseModes.MarkdownV2)
        .withEntities(Seq(messageEntity1))
        .withDisableWebPagePreview(true)
        .withDisableNotification(false)
        .withProtectContent(true)
        .withReplyToMessageId(811)
        .withAllowSendingWithoutReply(false)
        .withReplyMarkup(forceReplyMarkup1)

    val payload: SendMessagePayload = SendMessagePayload(
      chatId = "331",
      text = "hello world",
      parseMode = Some(ParseModes.MarkdownV2),
      entities = Some(Seq(messageEntity1)),
      disableWebPagePreview = Some(true),
      disableNotification = Some(false),
      protectContent = Some(true),
      replyToMessageId = Some(811),
      allowSendingWithoutReply = Some(false),
      replyMarkup = Some(forceReplyMarkup1)
    )
  }

  "SendMessage" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "sendMessage"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "SendMessagePayload" should {
      "serialize payload to json" in new Scope {
        payload.asJson shouldBe jsonResource("json/command/send-message-payload.json")
      }
    }
  }
}
