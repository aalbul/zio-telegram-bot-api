package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.EditMessageMedia.EditMessageMediaPayload
import io.github.aalbul.zio.telegram.domain.MessageOrInlineMessageUpdateResult
import io.github.aalbul.zio.telegram.test.BaseSpec

class EditMessageMediaSpec extends BaseSpec {
  trait Scope {
    val command: Command[MessageOrInlineMessageUpdateResult] =
      EditMessageMedia
        .of(inputMediaPhoto1)
        .withChatId("341")
        .withMessageId(882)
        .withInlineMessageId("453")
        .withReplyMarkup(inlineKeyboardMarkup1)

    val payload: EditMessageMediaPayload = EditMessageMediaPayload(
      chatId = Some("341"),
      messageId = Some(882),
      inlineMessageId = Some("453"),
      media = inputMediaPhoto1,
      replyMarkup = Some(inlineKeyboardMarkup1)
    )
  }

  "EditMessageMedia" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "editMessageMedia"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "EditMessageMediaPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/edit-message-media-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[EditMessageMediaPayload](
            "json/command/edit-message-media-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
