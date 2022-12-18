package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.SendMessage.SendMessagePayload
import io.github.aalbul.zio.telegram.domain.{Message, ParseMode}
import io.github.aalbul.zio.telegram.test.BaseSpec

class SendMessageSpec extends BaseSpec {
  trait Scope {
    val command: Command[Message] =
      SendMessage
        .of(chatId = "331", text = "hello world")
        .withParseMode(ParseMode.MarkdownV2)
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
      parseMode = Some(ParseMode.MarkdownV2),
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
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/send-message-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[SendMessagePayload]("json/command/send-message-payload.json") shouldBe payload
        }
      }
    }
  }
}
