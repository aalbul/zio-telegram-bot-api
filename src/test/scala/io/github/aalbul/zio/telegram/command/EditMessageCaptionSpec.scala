package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.EditMessageCaption.EditMessageCaptionPayload
import io.github.aalbul.zio.telegram.domain.{MessageOrInlineMessageUpdateResult, ParseMode}
import io.github.aalbul.zio.telegram.test.BaseSpec

class EditMessageCaptionSpec extends BaseSpec {
  trait Scope {
    val command: Command[MessageOrInlineMessageUpdateResult] =
      EditMessageCaption.of
        .withChatId("345")
        .withMessageId(881)
        .withInlineMessageId("451")
        .withCaption("caption-1")
        .withParseMode(ParseMode.HTML)
        .withCaptionEntities(Seq(messageEntity1))
        .withReplyMarkup(inlineKeyboardMarkup1)

    val payload: EditMessageCaptionPayload = EditMessageCaptionPayload(
      chatId = Some("345"),
      messageId = Some(881),
      inlineMessageId = Some("451"),
      caption = Some("caption-1"),
      parseMode = Some(ParseMode.HTML),
      captionEntities = Some(Seq(messageEntity1)),
      replyMarkup = Some(inlineKeyboardMarkup1)
    )
  }

  "EditMessageCaption" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "editMessageCaption"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "EditMessageCaptionPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/edit-message-caption-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[EditMessageCaptionPayload](
            "json/command/edit-message-caption-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
