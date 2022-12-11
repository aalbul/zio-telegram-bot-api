package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class PassportDataSpec extends BaseSpec {
  "PassportData" when {
    "decoder" should {
      "decode passport data from json" in {
        jsonResourceAs[PassportData]("json/model/passport-data.json") shouldBe passportData1
      }
    }
  }
}
