package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class PreCheckoutQuerySpec extends BaseSpec {
  "PreCheckoutQuery" when {
    "decoder" should {
      "decode pre checkout query json" in {
        jsonResourceAs[PreCheckoutQuery]("json/model/pre-checkout-query.json") shouldBe preCheckoutQuery1
      }
    }
  }
}
