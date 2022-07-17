package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.projection.message.SuccessfulPaymentMessage.successfulPaymentProjector
import io.github.aalbul.zio.telegram.test.BaseSpec

class SuccessfulPaymentMessageSpec extends BaseSpec {
  "SuccessfulPaymentMessage" when {
    "successfulPaymentProjector" should {
      "properly project successful payment messages" in {
        successfulPaymentProjector.project(successfulPaymentMessage1) shouldBe Some(successfulPaymentProjection)
      }

      "not match any other messages" in {
        (allMessages - successfulPaymentMessage1).flatMap(successfulPaymentProjector.project) shouldBe empty
      }
    }
  }
}
