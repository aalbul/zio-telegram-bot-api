package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class InvoiceSpec extends BaseSpec {
  "Invoice" when {
    "decoder" should {
      "should decode invoice json" in {
        jsonResourceAs[Invoice]("json/model/invoice.json") shouldBe invoice1
      }
    }
  }
}
