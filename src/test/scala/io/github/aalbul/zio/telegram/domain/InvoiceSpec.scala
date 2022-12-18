package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class InvoiceSpec extends BaseSpec {
  "Invoice" when {
    "encoder" should {
      "encode invoice to json" in {
        writeToString(invoice1) should matchJsonResource("json/model/invoice.json")
      }
    }

    "decoder" should {
      "decode invoice from json" in {
        jsonResourceAs[Invoice]("json/model/invoice.json") shouldBe invoice1
      }
    }
  }
}
