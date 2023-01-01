package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.EditMessageReplyMarkup.EditMessageReplyMarkupPayload
import io.github.aalbul.zio.telegram.domain.MessageOrInlineMessageUpdateResult
import io.github.aalbul.zio.telegram.test.BaseSpec

class EditMessageReplyMarkupSpec extends BaseSpec {
  trait Scope {
    val command: Command[MessageOrInlineMessageUpdateResult] =
      EditMessageReplyMarkup.of
        .withChatId("348")
        .withMessageId(879)
        .withInlineMessageId("459")
        .withReplyMarkup(inlineKeyboardMarkup1)

    val payload: EditMessageReplyMarkupPayload = EditMessageReplyMarkupPayload(
      chatId = Some("348"),
      messageId = Some(879),
      inlineMessageId = Some("459"),
      replyMarkup = Some(inlineKeyboardMarkup1)
    )
  }

  "EditMessageReplyMarkup" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "editMessageReplyMarkup"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "EditMessageReplyMarkupPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/edit-message-reply-markup-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[EditMessageReplyMarkupPayload](
            "json/command/edit-message-reply-markup-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
