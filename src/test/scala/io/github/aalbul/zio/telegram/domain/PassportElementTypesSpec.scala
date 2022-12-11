package io.github.aalbul.zio.telegram.domain

import io.circe.Json
import io.github.aalbul.zio.telegram.domain.PassportElementTypes.PassportElementType
import io.github.aalbul.zio.telegram.test.BaseSpec

class PassportElementTypesSpec extends BaseSpec {
  "PassportElementTypes" when {
    "decoder" should {
      "decode passport element types from json" in {
        Json.fromString("passport_registration").as[PassportElementType] shouldBe Right(
          PassportElementTypes.PassportRegistration
        )
        Json.fromString("utility_bill").as[PassportElementType] shouldBe Right(
          PassportElementTypes.UtilityBill
        )
      }
    }
  }
}
