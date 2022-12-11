package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class ShippingAddressSpec extends BaseSpec {
  "ShippingAddress" when {
    "decoder" should {
      "decode shipping address json" in {
        jsonResourceAs[ShippingAddress]("json/model/shipping-address.json") shouldBe shippingAddress1
      }
    }
  }
}
