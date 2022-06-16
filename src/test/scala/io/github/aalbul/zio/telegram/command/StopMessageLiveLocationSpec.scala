package io.github.aalbul.zio.telegram.command

import io.github.aalbul.zio.telegram.command.StopMessageLiveLocation.StopMessageLiveLocationPayload
import io.github.aalbul.zio.telegram.domain.LiveLocationUpdateResult
import io.github.aalbul.zio.telegram.test.BaseSpec
import io.circe.syntax.EncoderOps

class StopMessageLiveLocationSpec extends BaseSpec {
  trait Scope {
    val command: Command[LiveLocationUpdateResult] =
      StopMessageLiveLocation
        .of
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
      "serialize payload to json" in new Scope {
        payload.asJson shouldBe jsonResource("json/command/stop-message-live-location-payload.json")
      }
    }
  }
}
