package io.github.aalbul.zio.telegram.domain

import com.github.plokhotnyuk.jsoniter_scala.core.writeToString
import io.github.aalbul.zio.telegram.test.BaseSpec

class EncryptedPassportElementSpec extends BaseSpec {
  "EncryptedPassportElement" when {
    "encode" should {
      "encode encrypted passport element to json" in {
        writeToString(encryptedPassportElement1) should matchJsonResource("json/model/encrypted-passport-element.json")
      }
    }

    "decoder" should {
      "decode encrypted passport element from json" in {
        jsonResourceAs[EncryptedPassportElement](
          "json/model/encrypted-passport-element.json"
        ) shouldBe encryptedPassportElement1
      }
    }
  }
}
