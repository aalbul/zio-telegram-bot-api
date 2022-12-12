package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class SuccessfulPaymentSpec extends BaseSpec {
  "SuccessfulPayment" when {
    "decoder" should {
      "decode successful payment json" in {
        jsonResourceAs[SuccessfulPayment]("json/model/successful-payment.json") shouldBe successfulPayment1
      }
    }
  }
}
