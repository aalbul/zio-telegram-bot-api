package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class ShippingOptionSpec extends BaseSpec {
  "ShippingOption" when {
    "encoder" should {
      "encode shipping option to json" in {
        writeToString(shippingOption1) should matchJsonResource("json/model/shipping-option.json")
      }
    }

    "decoder" should {
      "decode shipping option from json" in {
        jsonResourceAs[ShippingOption]("json/model/shipping-option.json") shouldBe shippingOption1
      }
    }
  }
}
