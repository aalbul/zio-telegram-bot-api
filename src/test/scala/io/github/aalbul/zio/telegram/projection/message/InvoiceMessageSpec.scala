package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.projection.message.InvoiceMessage.invoiceMessageProjector
import io.github.aalbul.zio.telegram.test.BaseSpec

class InvoiceMessageSpec extends BaseSpec {
  "InvoiceMessage" when {
    "invoiceMessageProjector" should {
      "properly project invoice messages" in {
        invoiceMessageProjector.project(invoiceMessage1) shouldBe Some(invoiceMessageProjection)
      }

      "not match any other messages" in {
        (allMessages - invoiceMessage1).flatMap(invoiceMessageProjector.project) shouldBe empty
      }
    }
  }
}
