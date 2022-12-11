package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec
import MaskPointTypes.*
import io.circe.Json

class MaskPointTypesSpec extends BaseSpec {
  "MaskPointTypes" when {
    "maskPointTypeDecoder" should {
      "properly decode chat types" in {
        Json.fromString("forehead").as[MaskPointType].toTry.get shouldBe MaskPointTypes.Forehead
        Json.fromString("mouth").as[MaskPointType].toTry.get shouldBe MaskPointTypes.Mouth
      }
    }
  }
}
