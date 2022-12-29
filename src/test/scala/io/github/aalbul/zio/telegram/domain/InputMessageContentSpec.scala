package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class InputMessageContentSpec extends BaseSpec {
  "InputMessageContent" when {
    "encoder" should {
      "encode InputTextMessageContent to json" in {
        writeToString(inputMessageContent1) should matchJsonResource("json/model/input-text-message-content.json")
      }

      "encode InputLocationMessageContent to json" in {
        writeToString(inputMessageContent2) should matchJsonResource("json/model/input-location-message-content.json")
      }

      "encode InputVenueMessageContent to json" in {
        writeToString(inputMessageContent3) should matchJsonResource("json/model/input-venue-message-content.json")
      }

      "encode InputContactMessageContent to json" in {
        writeToString(inputMessageContent4) should matchJsonResource("json/model/input-contact-message-content.json")
      }

      "encode InputInvoiceMessageContent to json" in {
        writeToString(inputMessageContent5) should matchJsonResource("json/model/input-invoice-message-content.json")
      }
    }

    "decoder" should {
      "decode InputTextMessageContent from json" in {
        jsonResourceAs[InputMessageContent]("json/model/input-text-message-content.json") shouldBe inputMessageContent1
      }

      "decode InputLocationMessageContent from json" in {
        jsonResourceAs[InputMessageContent](
          "json/model/input-location-message-content.json"
        ) shouldBe inputMessageContent2
      }

      "decode InputVenueMessageContent from json" in {
        jsonResourceAs[InputMessageContent](
          "json/model/input-venue-message-content.json"
        ) shouldBe inputMessageContent3
      }

      "decode InputContactMessageContent from json" in {
        jsonResourceAs[InputMessageContent](
          "json/model/input-contact-message-content.json"
        ) shouldBe inputMessageContent4
      }

      "decode InputInvoiceMessageContent from json" in {
        jsonResourceAs[InputMessageContent](
          "json/model/input-invoice-message-content.json"
        ) shouldBe inputMessageContent5
      }
    }
  }
}
