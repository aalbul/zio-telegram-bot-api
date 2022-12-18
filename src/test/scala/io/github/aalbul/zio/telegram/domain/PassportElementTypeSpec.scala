package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.{readFromString, writeToString}
import io.github.aalbul.zio.telegram.test.BaseSpec

class PassportElementTypeSpec extends BaseSpec {
  "PassportElementType" when {
    "encoder" should {
      "properly encode passport element type to json" in {
        writeToString[PassportElementType](
          PassportElementType.PassportRegistration
        ) shouldBe "\"passport_registration\""
        writeToString[PassportElementType](PassportElementType.UtilityBill) shouldBe "\"utility_bill\""
      }
    }

    "decoder" should {
      "decode passport element type from json" in {
        readFromString[PassportElementType](
          "\"passport_registration\""
        ) shouldBe PassportElementType.PassportRegistration
        readFromString[PassportElementType]("\"utility_bill\"") shouldBe PassportElementType.UtilityBill
      }
    }
  }
}
