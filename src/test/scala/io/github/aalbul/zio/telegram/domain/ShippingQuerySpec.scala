package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class ShippingQuerySpec extends BaseSpec {
  "ShippingQuery" when {
    "decoder" should {
      "decode shipping query json" in {
        jsonResourceAs[ShippingQuery]("json/model/shipping-query.json") shouldBe shippingQuery1
      }
    }
  }
}
