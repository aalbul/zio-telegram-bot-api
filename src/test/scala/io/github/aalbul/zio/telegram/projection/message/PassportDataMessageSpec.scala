package io.github.aalbul.zio.telegram.projection.message

import io.github.aalbul.zio.telegram.test.BaseSpec
import PassportDataMessage.passportDataProjector

class PassportDataMessageSpec extends BaseSpec {
  "PassportDataMessage" when {
    "passportDataProjector" should {
      "properly project passport data messages" in {
        passportDataProjector.project(passportDataMessage1) shouldBe Some(passportDataProjection)
      }

      "not match any other messages" in {
        (allMessages - passportDataMessage1).flatMap(passportDataProjector.project) shouldBe empty
      }
    }
  }
}
