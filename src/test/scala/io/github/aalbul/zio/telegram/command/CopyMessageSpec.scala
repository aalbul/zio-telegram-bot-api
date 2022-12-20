package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.CopyMessage.CopyMessagePayload
import io.github.aalbul.zio.telegram.domain.{MessageId, ParseMode}
import io.github.aalbul.zio.telegram.test.BaseSpec

class CopyMessageSpec extends BaseSpec {
  trait Scope {
    val command: Command[MessageId] = CopyMessage
      .of(chatId = "12554", "5578", "14339")
      .withMessageThreadId(23)
      .withCaption("Copied message")
      .withParseMode(ParseMode.Markdown)
      .withCaptionEntities(Seq(messageEntity1))
      .withDisableNotification(true)
      .withProtectContent(false)
      .withReplyToMessageId(20)
      .withAllowSendingWithoutReply(true)
      .withReplyMarkup(forceReplyMarkup1)

    val payload: CopyMessagePayload = CopyMessagePayload(
      chatId = "12554",
      messageThreadId = Some(23),
      fromChatId = "5578",
      messageId = "14339",
      caption = Some("Copied message"),
      parseMode = Some(ParseMode.Markdown),
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
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/copy-message-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[CopyMessagePayload]("json/command/copy-message-payload.json") shouldBe payload
        }
      }
    }
  }
}
