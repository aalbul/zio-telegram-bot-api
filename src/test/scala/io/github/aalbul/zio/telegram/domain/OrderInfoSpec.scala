package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class OrderInfoSpec extends BaseSpec {
  "OrderInfo" when {
    "encoder" should {
      "encode order info to json" in {
        writeToString(orderInfo1) should matchJsonResource("json/model/order-info.json")
      }
    }

    "decoder" should {
      "decode order info from json" in {
        jsonResourceAs[OrderInfo]("json/model/order-info.json") shouldBe orderInfo1
      }
    }
  }
}
