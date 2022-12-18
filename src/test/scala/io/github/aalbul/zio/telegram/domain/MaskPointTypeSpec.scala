package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.{readFromString, writeToString}
import io.github.aalbul.zio.telegram.domain.MaskPointType.*
import io.github.aalbul.zio.telegram.test.BaseSpec

class MaskPointTypeSpec extends BaseSpec {
  "MaskPointType" when {
    "encoder" should {
      "should encode mask point type to json" in {
        writeToString[MaskPointType](MaskPointType.Forehead) shouldBe "\"forehead\""
        writeToString[MaskPointType](MaskPointType.Mouth) shouldBe "\"mouth\""
      }
    }

    "decoder" should {
      "properly decode mask point type from json" in {
        readFromString[MaskPointType]("\"forehead\"") shouldBe MaskPointType.Forehead
        readFromString[MaskPointType]("\"mouth\"") shouldBe MaskPointType.Mouth
      }
    }
  }
}
