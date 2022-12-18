package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class SuccessfulPaymentSpec extends BaseSpec {
  "SuccessfulPayment" when {
    "encoder" should {
      "encode successful payment to json" in {
        writeToString(successfulPayment1) should matchJsonResource("json/model/successful-payment.json")
      }
    }

    "decoder" should {
      "decode successful payment from json" in {
        jsonResourceAs[SuccessfulPayment]("json/model/successful-payment.json") shouldBe successfulPayment1
      }
    }
  }
}
