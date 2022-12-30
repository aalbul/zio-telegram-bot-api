package io.github.aalbul.zio.telegram.command

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.command.CreateInvoiceLink.CreateInvoiceLinkPayload
import io.github.aalbul.zio.telegram.test.BaseSpec

class CreateInvoiceLinkSpec extends BaseSpec {
  trait Scope {
    val command: Command[String] =
      CreateInvoiceLink
        .of(
          title = "invoice title",
          description = "invoice description",
          payload = "invoice payload",
          providerToken = "provider-token-1",
          currency = "USD",
          prices = Seq(labeledPrice1)
        )
        .withMaxTipAmount(20)
        .withSuggestedTipAmounts(Seq(5, 10, 15, 20))
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

    val payload: CreateInvoiceLinkPayload = CreateInvoiceLinkPayload(
      title = "invoice title",
      description = "invoice description",
      payload = "invoice payload",
      providerToken = "provider-token-1",
      currency = "USD",
      prices = Seq(labeledPrice1),
      maxTipAmount = Some(20),
      suggestedTipAmounts = Some(Seq(5, 10, 15, 20)),
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
      isFlexible = Some(true)
    )
  }

  "CreateInvoiceLink" when {
    "name" should {
      "have proper name" in new Scope {
        command.name shouldBe "createInvoiceLink"
      }
    }

    "parameters" should {
      "represent parameters as json" in new Scope {
        command.parameters shouldBe JsonBody(payload)
      }
    }

    "CreateInvoiceLinkPayload" should {
      "encoder" should {
        "encode payload to json" in new Scope {
          writeToString(payload) should matchJsonResource("json/command/create-invoice-link-payload.json")
        }
      }

      "decoder" should {
        "decode payload from json" in new Scope {
          jsonResourceAs[CreateInvoiceLinkPayload]("json/command/create-invoice-link-payload.json") shouldBe payload
        }
      }
    }
  }
}
