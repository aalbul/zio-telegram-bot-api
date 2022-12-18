package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class ShippingAddressSpec extends BaseSpec {
  "ShippingAddress" when {
    "encoder" should {
      "encode shipping address to json" in {
        writeToString(shippingAddress1) should matchJsonResource("json/model/shipping-address.json")
      }
    }

    "decoder" should {
      "decode shipping address from json" in {
        jsonResourceAs[ShippingAddress]("json/model/shipping-address.json") shouldBe shippingAddress1
      }
    }
  }
}
