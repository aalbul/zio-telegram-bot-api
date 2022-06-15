package com.github.aalbul.zio.telegram.command

import com.github.aalbul.zio.telegram.command.SendVenue.SendVenuePayload
import com.github.aalbul.zio.telegram.domain.Message
import com.github.aalbul.zio.telegram.test.BaseSpec
import io.circe.syntax.EncoderOps

class SendVenueSpec extends BaseSpec {
  trait Scope {
    val command: Command[Message] =
      SendVenue
        .of(chatId = "123", latitude = 10, longitude = 20, title = "Aquario", "Street 1")
        .withFoursquareId("223")
        .withFoursquareType("arts_entertainment/aquarium")
        .withGooglePlaceId("334")
        .withGooglePlaceType("aquarium")
        .withDisableNotification(false)
        .withProtectContent(true)
        .withReplyToMessageId(811)
        .withAllowSendingWithoutReply(false)
        .withReplyMarkup(forceReplyMarkup1)

    val payload: SendVenuePayload = SendVenuePayload(
      chatId = "123",
      latitude = 10,
      longitude = 20,
      title = "Aquario",
      address = "Street 1",
      foursquareId = Some("223"),
      foursquareType = Some("arts_entertainment/aquarium"),
      googlePlaceId = Some("334"),
      googlePlaceType = Some("aquarium"),
      disableNotification = Some(false),
      protectContent = Some(true),
      replyToMessageId = Some(811),
      allowSendingWithoutReply = Some(false),
      replyMarkup = Some(forceReplyMarkup1)
    )
  }

  "SendVenue" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "sendMessage"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "SendVenuePayload" should {
      "serialize payload to json" in new Scope {
        payload.asJson shouldBe jsonResource("json/command/send-venue-payload.json")
      }
    }
  }
}
