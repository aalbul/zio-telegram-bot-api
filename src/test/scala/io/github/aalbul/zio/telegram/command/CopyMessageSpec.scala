package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.CopyMessage.CopyMessagePayload
import io.github.aalbul.zio.telegram.domain.{MessageId, ParseModes}
import io.github.aalbul.zio.telegram.test.BaseSpec
import io.circe.syntax.EncoderOps

class CopyMessageSpec extends BaseSpec {
  trait Scope {
    val command: Command[MessageId] = CopyMessage
      .of(chatId = "12554", "5578", "14339")
      .withCaption("Copied message")
      .withParseMode(ParseModes.Markdown)
      .withCaptionEntities(Seq(messageEntity1))
      .withDisableNotification(true)
      .withProtectContent(false)
      .withReplyToMessageId(20)
      .withAllowSendingWithoutReply(true)
      .withReplyMarkup(forceReplyMarkup1)

    val payload: CopyMessagePayload = CopyMessagePayload(
      chatId = "12554",
      fromChatId = "5578",
      messageId = "14339",
      caption = Some("Copied message"),
      parseMode = Some(ParseModes.Markdown),
      captionEntities = Some(Seq(messageEntity1)),
      disableNotification = Some(true),
      protectContent = Some(false),
      replyToMessageId = Some(20),
      allowSendingWithoutReply = Some(true),
      replyMarkup = Some(forceReplyMarkup1)
    )
  }

  "CopyMessage" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "copyMessage"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "CopyMessagePayload" should {
      "serialize payload to json" in new Scope {
        payload.asJson shouldBe jsonResource("json/command/copy-message-payload.json")
      }
    }
  }
}
