package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class PassportFileSpec extends BaseSpec {
  "PassportFile" when {
    "encode" should {
      "encode passport file to json" in {
        writeToString(passportFile1) should matchJsonResource("json/model/passport-file.json")
      }
    }

    "decoder" should {
      "decode passport file from json" in {
        jsonResourceAs[PassportFile]("json/model/passport-file.json") shouldBe passportFile1
      }
    }
  }
}
