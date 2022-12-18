package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class PassportDataSpec extends BaseSpec {
  "PassportData" when {
    "encoder" should {
      "encode passport data to json" in {
        writeToString(passportData1) should matchJsonResource("json/model/passport-data.json")
      }
    }

    "decoder" should {
      "decode passport data from json" in {
        jsonResourceAs[PassportData]("json/model/passport-data.json") shouldBe passportData1
      }
    }
  }
}
