package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.command.SendLocation.SendLocationPayload
import com.github.aalbul.zio.telegram.domain.Message
import com.github.aalbul.zio.telegram.test.BaseSpec
import io.circe.syntax.EncoderOps

class SendLocationSpec extends BaseSpec {
  trait Scope {
    val command: Command[Message] =
      SendLocation
        .of(chatId = "853", latitude = 20.86, longitude = 11.23)
        .withHorizontalAccuracy(1.35)
        .withLivePeriod(50)
        .withHeading(11)
        .withProximityAlertRadius(52)
        .withDisableNotification(true)
        .withProtectContent(false)
        .withReplyToMessageId(50)
        .withAllowSendingWithoutReply(true)
        .withReplyMarkup(forceReplyMarkup1)

    val payload: SendLocationPayload = SendLocationPayload(
      chatId = "853",
      latitude = 20.86,
      longitude = 11.23,
      horizontalAccuracy = Some(1.35),
      livePeriod = Some(50),
      heading = Some(11),
      proximityAlertRadius = Some(52),
      disableNotification = Some(true),
      protectContent = Some(false),
      replyToMessageId = Some(50),
      allowSendingWithoutReply = Some(true),
      replyMarkup = Some(forceReplyMarkup1)
    )
  }

  "SendLocation" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "sendLocation"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "SendLocationPayload" should {
      "serialize payload to json" in new Scope {
        payload.asJson shouldBe jsonResource("json/command/send-location-payload.json")
      }
    }
  }
}
