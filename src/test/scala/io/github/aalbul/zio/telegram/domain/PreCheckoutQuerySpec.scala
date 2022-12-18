package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class PreCheckoutQuerySpec extends BaseSpec {
  "PreCheckoutQuery" when {
    "encoder" should {
      "encode pre checkout query to json" in {
        writeToString(preCheckoutQuery1) should matchJsonResource("json/model/pre-checkout-query.json")
      }
    }

    "decoder" should {
      "decode pre checkout query from json" in {
        jsonResourceAs[PreCheckoutQuery]("json/model/pre-checkout-query.json") shouldBe preCheckoutQuery1
      }
    }
  }
}
