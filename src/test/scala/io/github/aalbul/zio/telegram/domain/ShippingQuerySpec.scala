package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class ShippingQuerySpec extends BaseSpec {
  "ShippingQuery" when {
    "encoder" should {
      "encode shipping query to json" in {
        writeToString(shippingQuery1) should matchJsonResource("json/model/shipping-query.json")
      }
    }

    "decoder" should {
      "decode shipping query from json" in {
        jsonResourceAs[ShippingQuery]("json/model/shipping-query.json") shouldBe shippingQuery1
      }
    }
  }
}
