package io.github.aalbul.zio.telegram.domain

import io.github.aalbul.zio.telegram.test.BaseSpec

class PassportFileSpec extends BaseSpec {
  "PassportFile" when {
    "decoder" should {
      "decode passport file from json" in {
        jsonResourceAs[PassportFile]("json/model/passport-file.json") shouldBe passportFile1
      }
    }
  }
}
