package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class OrderInfoSpec extends BaseSpec {
  "OrderInfo" when {
    "decoder" should {
      "decode order info from json" in {
        jsonResourceAs[OrderInfo]("json/model/order-info.json") shouldBe orderInfo1
      }
    }
  }
}
