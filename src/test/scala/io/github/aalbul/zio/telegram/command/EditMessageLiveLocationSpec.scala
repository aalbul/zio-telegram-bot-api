package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.EditMessageLiveLocation.EditMessageLiveLocationPayload
import io.github.aalbul.zio.telegram.domain.MessageOrInlineMessageUpdateResult
import io.github.aalbul.zio.telegram.test.BaseSpec

class EditMessageLiveLocationSpec extends BaseSpec {
  trait Scope {
    val command: Command[MessageOrInlineMessageUpdateResult] =
      EditMessageLiveLocation
        .of(20, 30)
        .withChatId("345")
        .withMessageId(889)
        .withInlineMessageId("456")
        .withHorizontalAccuracy(5.4)
        .withHeading(10)
        .withProximityAlertRadius(14)
        .withReplyMarkup(inlineKeyboardMarkup1)

    val payload: EditMessageLiveLocationPayload = EditMessageLiveLocationPayload(
      latitude = 20,
      longitude = 30,
      chatId = Some("345"),
      messageId = Some(889),
      inlineMessageId = Some("456"),
      horizontalAccuracy = Some(5.4),
      heading = Some(10),
      proximityAlertRadius = Some(14),
      replyMarkup = Some(inlineKeyboardMarkup1)
    )
  }

  "EditMessageLiveLocation" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "editMessageLiveLocation"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "EditMessageLiveLocationPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/edit-message-live-location-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[EditMessageLiveLocationPayload](
            "json/command/edit-message-live-location-payload.json"
          ) shouldBe payload
        }
      }
    }
  }
}
