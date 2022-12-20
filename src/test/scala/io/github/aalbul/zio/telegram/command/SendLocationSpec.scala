package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.SendLocation.SendLocationPayload
import io.github.aalbul.zio.telegram.domain.Message
import io.github.aalbul.zio.telegram.test.BaseSpec

import scala.concurrent.duration.DurationInt

class SendLocationSpec extends BaseSpec {
  trait Scope {
    val command: Command[Message] =
      SendLocation
        .of(chatId = "853", latitude = 20.86, longitude = 11.23)
        .withMessageThreadId(30)
        .withHorizontalAccuracy(1.35)
        .withLivePeriod(80.seconds)
        .withHeading(11)
        .withProximityAlertRadius(52)
        .withDisableNotification(true)
        .withProtectContent(false)
        .withReplyToMessageId(50)
        .withAllowSendingWithoutReply(true)
        .withReplyMarkup(forceReplyMarkup1)

    val payload: SendLocationPayload = SendLocationPayload(
      chatId = "853",
      messageThreadId = Some(30),
      latitude = 20.86,
      longitude = 11.23,
      horizontalAccuracy = Some(1.35),
      livePeriod = Some(80.seconds),
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
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/send-location-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[SendLocationPayload]("json/command/send-location-payload.json") shouldBe payload
        }
      }
    }
  }
}
