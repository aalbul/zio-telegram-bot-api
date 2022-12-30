package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.StopMessageLiveLocation.StopMessageLiveLocationPayload
import io.github.aalbul.zio.telegram.domain.MessageOrInlineMessageUpdateResult
import io.github.aalbul.zio.telegram.test.BaseSpec

class StopMessageLiveLocationSpec extends BaseSpec {
  trait Scope {
    val command: Command[MessageOrInlineMessageUpdateResult] =
      StopMessageLiveLocation.of
        .withChatId("345")
        .withMessageId(889)
        .withInlineMessageId("456")
        .withReplyMarkup(inlineKeyboardMarkup1)

    val payload: StopMessageLiveLocationPayload = StopMessageLiveLocationPayload(
      chatId = Some("345"),
      messageId = Some(889),
      inlineMessageId = Some("456"),
      replyMarkup = Some(inlineKeyboardMarkup1)
    )
  }

  "StopMessageLiveLocation" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "stopMessageLiveLocation"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "StopMessageLiveLocationPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource(
            "json/command/stop-message-live-location-payload.json"
          )
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[StopMessageLiveLocationPayload](
            "json/command/stop-message-live-location-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
