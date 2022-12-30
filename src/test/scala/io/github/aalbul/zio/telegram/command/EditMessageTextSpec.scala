package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.EditMessageText.EditMessageTextPayload
import io.github.aalbul.zio.telegram.domain.{MessageOrInlineMessageUpdateResult, ParseMode}
import io.github.aalbul.zio.telegram.test.BaseSpec

class EditMessageTextSpec extends BaseSpec {
  trait Scope {
    val command: Command[MessageOrInlineMessageUpdateResult] =
      EditMessageText
        .of(text = "new text")
        .withChatId("346")
        .withMessageId(888)
        .withInlineMessageId("454")
        .withParseMode(ParseMode.HTML)
        .withEntities(Seq(messageEntity1))
        .withDisableWebPagePreview(true)
        .withReplyMarkup(inlineKeyboardMarkup1)

    val payload: EditMessageTextPayload = EditMessageTextPayload(
      chatId = Some("346"),
      messageId = Some(888),
      inlineMessageId = Some("454"),
      text = "new text",
      parseMode = Some(ParseMode.HTML),
      entities = Some(Seq(messageEntity1)),
      disableWebPagePreview = Some(true),
      replyMarkup = Some(inlineKeyboardMarkup1)
    )
  }

  "EditMessageText" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "editMessageText"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "EditMessageTextPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/edit-message-text-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[EditMessageTextPayload](
            "json/command/edit-message-text-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
