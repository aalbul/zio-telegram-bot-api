package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.SendInvoice.SendInvoicePayload
import io.github.aalbul.zio.telegram.command.SendLocation.SendLocationPayload
import io.github.aalbul.zio.telegram.domain.Message
import io.github.aalbul.zio.telegram.test.BaseSpec

class SendInvoiceSpec extends BaseSpec {
  trait Scope {
    val command: Command[Message] =
      SendInvoice
        .of(
          chatId = "51",
          title = "invoice title",
          description = "invoice description",
          payload = "invoice payload",
          providerToken = "provider-token-1",
          currency = "USD",
          prices = Seq(labeledPrice1)
        )
        .withMessageThreadId(11)
        .withMaxTipAmount(20)
        .withSuggestedTipAmounts(Seq(5, 10, 15, 20))
        .withStartParameter("start-param")
        .withProviderData("provider-data")
        .withPhotoUrl("https://google.com/photo.jpg")
        .withPhotoSize(102)
        .withPhotoWidth(1024)
        .withPhotoHeight(768)
        .withNeedName(true)
        .withNeedPhoneNumber(false)
        .withNeedEmail(true)
        .withNeedShippingAddress(false)
        .withSendPhoneNumberToProvider(true)
        .withSendEmailToProvider(false)
        .withIsFlexible(true)
        .withDisableNotification(false)
        .withProtectContent(true)
        .withReplyToMessageId(22)
        .withAllowSendingWithoutReply(false)
        .withReplyMarkup(inlineKeyboardMarkup1)

    val payload: SendInvoicePayload = SendInvoicePayload(
      chatId = "51",
      messageThreadId = Some(11),
      title = "invoice title",
      description = "invoice description",
      payload = "invoice payload",
      providerToken = "provider-token-1",
      currency = "USD",
      prices = Seq(labeledPrice1),
      maxTipAmount = Some(20),
      suggestedTipAmounts = Some(Seq(5, 10, 15, 20)),
      startParameter = Some("start-param"),
      providerData = Some("provider-data"),
      photoUrl = Some("https://google.com/photo.jpg"),
      photoSize = Some(102),
      photoWidth = Some(1024),
      photoHeight = Some(768),
      needName = Some(true),
      needPhoneNumber = Some(false),
      needEmail = Some(true),
      needShippingAddress = Some(false),
      sendPhoneNumberToProvider = Some(true),
      sendEmailToProvider = Some(false),
      isFlexible = Some(true),
      disableNotification = Some(false),
      protectContent = Some(true),
      replyToMessageId = Some(22),
      allowSendingWithoutReply = Some(false),
      replyMarkup = Some(inlineKeyboardMarkup1)
    )
  }

  "SendInvoice" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "sendInvoice"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "SendInvoicePayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/send-invoice-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[SendInvoicePayload]("json/command/send-invoice-payload.json") shouldBe payload
        }
      }
    }
  }
}
